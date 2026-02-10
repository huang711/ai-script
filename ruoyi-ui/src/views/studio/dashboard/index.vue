<template>
  <div class="studio-dashboard">
    <!-- 空间切换 Tabs -->
    <div class="workspace-tabs">
      <div 
        class="tab-item" 
        :class="{ active: activeTab === 'personal' }"
        @click="switchTab('personal')"
      >
        <span class="tab-title">个人</span>
        <div class="tab-indicator" v-if="activeTab === 'personal'"></div>
      </div>
      <div 
        class="tab-item" 
        :class="{ active: activeTab === 'team' }"
        @click="switchTab('team')"
      >
        <span class="tab-title">团队项目</span>
        <div class="tab-indicator" v-if="activeTab === 'team'"></div>
      </div>
    </div>

    <div class="project-grid">
      <!-- 新建项目卡片 -->
      <div class="project-card new-project-card" @click="handleAddProject">
        <div class="new-project-content">
          <div class="plus-icon-wrapper">
            <i class="el-icon-plus"></i>
          </div>
          <span class="new-text">新建项目</span>
        </div>
      </div>

      <!-- 项目列表 -->
      <div class="project-card" v-for="project in projectList" :key="project.id" @click="handleProjectClick(project)">
        <div class="card-cover" :style="{ backgroundColor: getRandomColor(project.id) }">
          <span class="cover-text">{{ project.name.substring(0, 1) }}</span>
        </div>
        <div class="card-info">
          <div class="project-name">{{ project.name }}</div>
          <div class="project-meta">
            <span>编辑于 {{ parseTime(project.updateTime || project.createTime, '{y}-{m}-{d}') }}</span>
            
            <!-- 操作菜单 -->
            <el-dropdown class="project-actions" @command="(command) => handleCommand(command, project)" @click.native.stop>
              <span class="el-dropdown-link">
                <i class="el-icon-more"></i>
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item command="rename">重命名</el-dropdown-item>
                <el-dropdown-item command="moveToTeam" v-if="activeTab === 'personal'">移动至团队</el-dropdown-item>
                <el-dropdown-item command="delete" divided style="color: #F56C6C;">删除</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </div>
      </div>
    </div>

    <!-- 新建/编辑项目弹窗 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body custom-class="studio-dialog">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入项目名称" />
        </el-form-item>
        <el-form-item label="项目描述" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入项目描述" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listProjects, addProjects, updateProjects, delProjects, moveToTeam } from "@/api/drama/projects";
import { listWorkspaces, addWorkspaces } from "@/api/workspace/workspaces";

export default {
  name: "StudioDashboard",
  data() {
    return {
      activeTab: 'personal', // personal | team
      projectList: [],
      workspaceList: [],
      open: false,
      title: "新建项目",
      form: {},
      rules: {
        name: [
          { required: true, message: "项目名称不能为空", trigger: "blur" }
        ]
      },
      colors: ['#409EFF', '#67C23A', '#E6A23C', '#F56C6C', '#909399', '#36cfc9', '#9254de', '#ff7a45']
    };
  },
  created() {
    this.initWorkspaces();
  },
  methods: {
    initWorkspaces() {
      listWorkspaces().then(response => {
        this.workspaceList = response.rows;
        
        // 尝试从 localStorage 恢复当前空间
        const cachedId = localStorage.getItem('workspaceId');
        if (cachedId) {
          const currentWs = this.workspaceList.find(w => w.id == cachedId);
          if (currentWs) {
            this.activeTab = currentWs.type; // 'personal' or 'team'
          } else {
            this.selectDefaultWorkspace();
          }
        } else {
          this.selectDefaultWorkspace();
        }
        
        // 加载项目列表
        this.getList();
      });
    },
    selectDefaultWorkspace() {
      // 默认选中个人空间
      const personalWs = this.workspaceList.find(w => w.type === 'personal');
      if (personalWs) {
        this.switchTab('personal', false);
      }
    },
    async switchTab(type, reload = true) {
      // 切换前先清空列表，防止显示上一个空间的内容
      this.projectList = [];
      
      let targetWs = this.workspaceList.find(w => w.type === type);
      
      // 如果切换到团队空间且不存在，则自动创建
      if (!targetWs && type === 'team') {
         try {
             await addWorkspaces({ name: '我的团队', type: 'team' });
             const res = await listWorkspaces();
             this.workspaceList = res.rows;
             targetWs = this.workspaceList.find(w => w.type === 'team');
         } catch (e) {
             this.$modal.msgError("初始化团队空间失败");
             // 回退到个人空间
             if (this.activeTab !== 'personal') {
                this.switchTab('personal');
             }
             return;
         }
      }

      if (targetWs) {
        this.activeTab = type;
        localStorage.setItem('workspaceId', targetWs.id);
        if (reload) {
            this.getList();
        }
      }
    },
    getList() {
      listProjects().then(response => {
        this.projectList = response.rows;
      });
    },
    getRandomColor(id) {
      const index = id % this.colors.length;
      return this.colors[index];
    },
    handleAddProject() {
      this.reset();
      this.title = "新建项目";
      this.open = true;
    },
    handleProjectClick(project) {
      this.$router.push(`/studio/editor/${project.id}`);
    },
    handleCommand(command, project) {
      switch (command) {
        case "rename":
          this.handleRename(project);
          break;
        case "moveToTeam":
          this.handleMoveToTeam(project);
          break;
        case "delete":
          this.handleDelete(project);
          break;
      }
    },
    handleRename(project) {
      this.reset();
      this.form = {
        id: project.id,
        name: project.name,
        description: project.description
      };
      this.title = "重命名项目";
      this.open = true;
    },
    handleMoveToTeam(project) {
      this.$confirm('确认要将该项目移动到团队空间吗？移动后无法撤回。', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        return moveToTeam(project.id);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("移动成功");
      }).catch(() => {});
    },
    handleDelete(project) {
      this.$confirm('是否确认删除项目名称为"' + project.name + '"的数据项？', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        return delProjects(project.id);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    reset() {
      this.form = {
        id: undefined,
        name: undefined,
        description: undefined
      };
      this.resetForm("form");
    },
    cancel() {
      this.open = false;
      this.reset();
    },
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateProjects(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addProjects(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    }
  }
};
</script>

<style lang="scss" scoped>
.studio-dashboard {
  padding: 0;
}

.workspace-tabs {
  display: flex;
  margin-bottom: 30px;
  border-bottom: 1px solid rgba(0,0,0,0.05);
  
  .tab-item {
    padding: 10px 0;
    margin-right: 40px;
    cursor: pointer;
    position: relative;
    color: #909399;
    font-size: 16px;
    font-weight: 500;
    transition: color 0.3s;
    
    &:hover {
      color: #303133;
    }
    
    &.active {
      color: #303133;
      font-weight: 600;
      
      .tab-indicator {
        position: absolute;
        bottom: -1px;
        left: 0;
        width: 100%;
        height: 2px;
        background-color: #303133;
      }
    }
  }
}

.project-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
  gap: 24px;
}

.project-card {
  background: #fff;
  border-radius: 12px;
  overflow: hidden;
  cursor: pointer;
  transition: all 0.3s cubic-bezier(0.25, 0.8, 0.25, 1);
  box-shadow: 0 1px 3px rgba(0,0,0,0.1);
  height: 200px;
  display: flex;
  flex-direction: column;
  border: 1px solid #EBEEF5;
  position: relative;

  &:hover {
    transform: translateY(-4px);
    box-shadow: 0 8px 20px rgba(0,0,0,0.12);
  }
}

.new-project-card {
  background: transparent;
  border: 2px dashed #dcdfe6;
  box-shadow: none;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #909399;
  
  &:hover {
    border-color: #909399;
    color: #606266;
    background: rgba(0,0,0,0.02);
  }

  .new-project-content {
    text-align: center;
    
    .plus-icon-wrapper {
      width: 48px;
      height: 48px;
      border-radius: 50%;
      background: #f5f7fa;
      display: flex;
      align-items: center;
      justify-content: center;
      margin: 0 auto 12px;
      transition: all 0.3s;
      
      i {
        font-size: 24px;
        color: #909399;
      }
    }
    
    .new-text {
      font-size: 14px;
      font-weight: 500;
    }
  }
  
  &:hover .plus-icon-wrapper {
    background: #e6e8eb;
    transform: scale(1.1);
  }
}

.card-cover {
  height: 120px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #fff;
  font-size: 32px;
  font-weight: bold;
  position: relative;
  
  &::after {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: linear-gradient(to bottom, transparent 0%, rgba(0,0,0,0.2) 100%);
  }
}

.card-info {
  padding: 16px;
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  background: #fff;

  .project-name {
    font-size: 15px;
    font-weight: 600;
    color: #1a1a1a;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    margin-bottom: 4px;
  }

  .project-meta {
    font-size: 12px;
    color: #909399;
    display: flex;
    justify-content: space-between;
    align-items: center;
    
    .project-actions {
      opacity: 0;
      transition: opacity 0.2s;
      
      .el-dropdown-link {
        cursor: pointer;
        padding: 4px;
        &:hover {
          color: #303133;
        }
      }
    }
  }
}

.project-card:hover .project-actions {
  opacity: 1;
}
</style>