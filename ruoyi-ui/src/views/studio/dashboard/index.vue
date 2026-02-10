<template>
  <div class="studio-dashboard">
    <div class="project-grid">
      <!-- 新建项目卡片 -->
      <div class="project-card new-project-card" @click="handleAddProject">
        <div class="new-project-content">
          <i class="el-icon-plus"></i>
          <span>新建项目</span>
        </div>
      </div>

      <!-- 项目列表 -->
      <div class="project-card" v-for="project in projectList" :key="project.id" @click="handleProjectClick(project)">
        <div class="card-cover" :style="{ backgroundColor: getRandomColor(project.id) }">
          <span class="cover-text">{{ project.name.substring(0, 1) }}</span>
        </div>
        <div class="card-info">
          <div class="project-name">{{ project.name }}</div>
          <div class="project-time">{{ parseTime(project.updateTime || project.createTime) }}</div>
        </div>
      </div>
    </div>

    <!-- 新建项目弹窗 -->
    <el-dialog title="新建项目" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目名称" prop="name">
          <el-form-item label="项目名称" prop="name">
            <el-input v-model="form.name" placeholder="请输入项目名称" />
          </el-form-item>
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
import { listProjects, addProjects } from "@/api/drama/projects";

export default {
  name: "StudioDashboard",
  data() {
    return {
      projectList: [],
      open: false,
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
    this.getList();
  },
  methods: {
    getList() {
      // 检查是否有 workspaceId，如果没有可能需要提示或重定向
      const workspaceId = localStorage.getItem('workspaceId');
      if (!workspaceId) {
        // 等待 WorkspaceSelect 初始化或提示
        return;
      }
      
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
      this.open = true;
    },
    handleProjectClick(project) {
      // 跳转到编辑器页面 (暂留空或404)
      this.$router.push(`/studio/project/${project.id}`);
    },
    reset() {
      this.form = {
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
          addProjects(this.form).then(response => {
            this.$modal.msgSuccess("新增成功");
            this.open = false;
            this.getList();
          });
        }
      });
    }
  }
};
</script>

<style lang="scss" scoped>
.studio-dashboard {
  padding: 20px 0;
}

.project-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(240px, 1fr));
  gap: 20px;
}

.project-card {
  background: #fff;
  border-radius: 8px;
  overflow: hidden;
  cursor: pointer;
  transition: all 0.3s;
  box-shadow: 0 2px 12px 0 rgba(0,0,0,0.05);
  height: 220px;
  display: flex;
  flex-direction: column;

  &:hover {
    transform: translateY(-5px);
    box-shadow: 0 5px 15px 0 rgba(0,0,0,0.1);
  }
}

.new-project-card {
  border: 2px dashed #dcdfe6;
  box-shadow: none;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #909399;
  
  &:hover {
    border-color: #409EFF;
    color: #409EFF;
  }

  .new-project-content {
    text-align: center;
    
    i {
      font-size: 32px;
      display: block;
      margin-bottom: 10px;
    }
    
    span {
      font-size: 16px;
    }
  }
}

.card-cover {
  height: 140px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #fff;
  font-size: 36px;
  font-weight: bold;
}

.card-info {
  padding: 15px;
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: space-between;

  .project-name {
    font-size: 16px;
    font-weight: 500;
    color: #303133;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  .project-time {
    font-size: 12px;
    color: #909399;
  }
}
</style>
