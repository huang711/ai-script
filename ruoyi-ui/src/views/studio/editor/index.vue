<template>
  <div class="studio-editor">
    <!-- Test Panel -->
    <div class="test-panel" style="margin-bottom: 10px; padding: 10px; background: #fff; border-radius: 4px;">
      <span style="font-weight: bold; margin-right: 10px;">接口测试:</span>
      <el-button size="mini" type="primary" plain @click="testCreateScript">创建测试剧本</el-button>
      <el-button size="mini" type="success" plain @click="testFetchScript">获取剧本数据</el-button>
      <el-button size="mini" type="warning" plain @click="testFetchEpisodes">获取分集列表</el-button>
    </div>

    <div class="editor-layout">
      <!-- Left Panel: Script Content -->
      <div class="left-panel">
        <div class="panel-header">
          <div class="header-left">
            <i class="el-icon-document header-icon"></i>
            <el-input
              v-model="currentScript.title"
              placeholder="未命名剧本"
              class="script-title-input"
              @blur="updateScriptTitle"
            />
          </div>
          <div class="header-right">
            <span class="status-text"><i class="el-icon-check"></i> 自动保存</span>
          </div>
        </div>
        
        <div class="panel-body script-body">
          <el-input
            v-model="currentScript.content"
            type="textarea"
            placeholder="在此处输入剧本内容...
例如：
[场景：咖啡馆，日]
张三坐在窗边，看着窗外的人群..."
            class="script-textarea"
            resize="none"
            @blur="updateScriptContent"
          />
        </div>
        
        <div class="panel-footer">
          <el-button 
            type="primary" 
            class="ai-btn"
            :loading="parsing" 
            @click="handleParseScript"
            icon="el-icon-magic-stick"
          >
            AI 智能拆解剧本
          </el-button>
        </div>
      </div>

      <!-- Right Panel: Episodes List -->
      <div class="right-panel">
        <div class="panel-header">
          <div class="header-title">
            <i class="el-icon-film header-icon"></i> 分集列表
            <el-tag size="mini" type="info" v-if="episodeList.length" effect="plain" class="count-tag">
              {{ episodeList.length }} 集
            </el-tag>
          </div>
          <div class="header-right">
             <el-button size="mini" icon="el-icon-plus" circle @click="testCreateEpisode" title="添加测试分集"></el-button>
             <el-button size="mini" icon="el-icon-refresh" circle @click="testFetchEpisodes" title="刷新列表"></el-button>
          </div>
        </div>
        
        <div class="panel-body episode-list-body">
          <el-empty 
            v-if="episodeList.length === 0" 
            description="暂无分集，请点击左侧按钮拆解剧本" 
            :image-size="120"
          ></el-empty>
          
          <div v-else class="episode-list">
            <div 
              v-for="item in episodeList" 
              :key="item.id" 
              class="episode-card"
            >
              <div class="card-header">
                <div class="episode-info">
                  <span class="episode-num">EP{{ item.episodeNum }}</span>
                  <span class="episode-name">{{ item.title }}</span>
                </div>
                <el-dropdown trigger="click">
                  <span class="el-dropdown-link">
                    <i class="el-icon-more"></i>
                  </span>
                  <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item icon="el-icon-edit" @click.native="handleRenameEpisode(item)">重命名</el-dropdown-item>
                    <el-dropdown-item icon="el-icon-delete" class="text-danger" @click.native="handleDeleteEpisode(item)">删除</el-dropdown-item>
                  </el-dropdown-menu>
                </el-dropdown>
              </div>
              
              <div class="card-content">
                <div class="summary-label">分集梗概</div>
                <el-input
                  v-model="item.summary"
                  type="textarea"
                  :rows="4"
                  placeholder="输入分集梗概..."
                  class="summary-input"
                  @blur="updateEpisodeSummary(item)"
                />
              </div>
              
              <div class="card-footer">
                <el-button 
                  size="small" 
                  plain 
                  icon="el-icon-video-camera" 
                  class="action-btn"
                  @click="handleOpenStoryboard(item)"
                >
                  进入分镜编辑
                </el-button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {
  getScriptByProjectId,
  listEpisodesByScriptId,
  parseScript
} from '@/api/drama/editor'
import { updateScripts, addScripts } from '@/api/drama/scripts'
import { updateEpisodes, addEpisodes, delEpisodes } from '@/api/drama/episodes'

export default {
  name: 'StudioEditor',
  data() {
    return {
      projectId: null,
      currentScript: {
        title: '',
        content: ''
      },
      episodeList: [],
      parsing: false
    }
  },
  created() {
    this.projectId = this.$route.params.projectId || this.$route.params.id
    this.initData()
  },
  methods: {
    async initData() {
      if (!this.projectId) {
        this.$message.error('缺少项目参数')
        return
      }
      const script = await getScriptByProjectId(this.projectId)
      if (!script) {
        // Fallback or empty state
        this.currentScript = { title: '新剧本', content: '' }
        return
      }
      this.currentScript = script
      await this.loadEpisodes(script.id)
    },
    async loadEpisodes(scriptId) {
      const res = await listEpisodesByScriptId(scriptId)
      this.episodeList = res.rows || []
    },
    async handleParseScript() {
      if (!this.currentScript || !this.currentScript.id) {
         this.$message.warning('请先保存剧本')
         return
      }
      this.parsing = true
      try {
        await parseScript(this.currentScript.id)
        await this.loadEpisodes(this.currentScript.id)
        this.$message.success('AI 拆解完成')
      } catch (error) {
        this.$message.error('拆解失败')
      } finally {
        this.parsing = false
      }
    },
    updateScriptTitle() {
      if (!this.currentScript.id) return
      updateScripts(this.currentScript)
    },
    updateScriptContent() {
      if (!this.currentScript.id) return
      updateScripts(this.currentScript)
    },
    updateEpisodeSummary(episode) {
      if (!episode.id) return
      updateEpisodes(episode)
    },
    
    handleRenameEpisode(episode) {
      this.$prompt('请输入新的分集标题', '重命名', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputValue: episode.title,
        inputPattern: /\S/,
        inputErrorMessage: '标题不能为空'
      }).then(({ value }) => {
        updateEpisodes({
          id: episode.id,
          title: value
        }).then(() => {
          this.$message.success('重命名成功')
          this.loadEpisodes(this.currentScript.id)
        })
      }).catch(() => {})
    },

    handleDeleteEpisode(episode) {
      this.$confirm(`确认要删除 "${episode.title}" 吗？此操作不可恢复。`, '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        return delEpisodes(episode.id)
      }).then(() => {
        this.$message.success('删除成功')
        this.loadEpisodes(this.currentScript.id)
      }).catch(() => {})
    },

    // Storyboard Methods
    handleOpenStoryboard(episode) {
      this.$router.push({ 
        name: 'StudioStoryboard', 
        params: { episodeId: episode.id },
        query: { projectId: this.projectId }
      })
    },
    // Test Methods
    async testCreateScript() {
      if (!this.projectId) return this.$message.warning('缺少项目ID')
      try {
        await addScripts({
          projectId: this.projectId,
          title: '测试剧本',
          content: '这是一个测试剧本内容。',
          version: 'v1.0'
        })
        this.$message.success('创建成功')
        this.initData() // Refresh
      } catch (e) {
        console.error(e)
        this.$message.error('创建失败: ' + e.message)
      }
    },
    async testFetchScript() {
       if (!this.projectId) return this.$message.warning('缺少项目ID')
       try {
         const script = await getScriptByProjectId(this.projectId)
         if (script) {
           this.$alert(`ID: ${script.id}\n标题: ${script.title}`, '获取剧本成功', { confirmButtonText: '确定' })
         } else {
           this.$message.info('当前项目无剧本')
         }
       } catch (e) {
         this.$message.error('获取失败')
       }
    },
    async testFetchEpisodes() {
      if (!this.currentScript || !this.currentScript.id) return this.$message.warning('请先获取剧本')
      try {
        const res = await listEpisodesByScriptId(this.currentScript.id)
        this.episodeList = res.rows || [] // Update the list directly
        this.$message.success(`已刷新，共 ${this.episodeList.length} 个分集`)
      } catch (e) {
        this.$message.error('获取失败')
      }
    },
    async testCreateEpisode() {
      if (!this.currentScript || !this.currentScript.id) return this.$message.warning('请先获取剧本')
      try {
        const nextNum = this.episodeList.length + 1
        await addEpisodes({
          scriptId: this.currentScript.id,
          episodeNum: nextNum,
          title: `第 ${nextNum} 集`,
          summary: '这是自动生成的测试分集。'
        })
        this.$message.success('分集创建成功')
        this.testFetchEpisodes() // Refresh list
      } catch (e) {
        this.$message.error('创建失败: ' + e.message)
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.studio-editor {
  height: calc(100vh - 50px);
  background: #f0f2f5;
  padding: 8px; /* Reduced padding for more space */
  box-sizing: border-box;
}

.editor-layout {
  display: flex;
  height: 100%;
  gap: 12px;
  max-width: 100%; /* Full width */
  margin: 0;
}

/* Common Panel Styles */
.left-panel, .right-panel {
  background: #fff;
  border-radius: 8px;
  display: flex;
  flex-direction: column;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.05);
  overflow: hidden;
}

.panel-header {
  height: 56px;
  flex-shrink: 0;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 20px;
  border-bottom: 1px solid #f0f0f0;
}

.header-title {
  font-size: 16px;
  font-weight: 600;
  color: #1f2d3d;
  display: flex;
  align-items: center;
  gap: 8px;
}

.header-icon {
  color: #409EFF;
  font-size: 18px;
}

.panel-body {
  flex: 1;
  overflow-y: auto;
}

/* Left Panel Specifics */
.left-panel {
  flex: 1;
  min-width: 0; /* Prevent flex overflow */
}

.header-left {
  display: flex;
  align-items: center;
  gap: 12px;
  flex: 1;
}

.script-title-input ::v-deep .el-input__inner {
  border: none;
  font-size: 18px;
  font-weight: 600;
  color: #303133;
  padding: 0;
  height: 40px;
  line-height: 40px;
  
  &:focus {
    background: transparent;
  }
}

.status-text {
  font-size: 12px;
  color: #909399;
  display: flex;
  align-items: center;
  gap: 4px;
}

.script-body {
  padding: 0;
  display: flex;
  flex-direction: column;
}

.script-textarea {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.script-textarea ::v-deep .el-textarea__inner {
  border: none;
  height: 100%;
  padding: 24px;
  font-size: 16px; /* Larger font */
  line-height: 1.8;
  color: #303133;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
  flex: 1;
  
  &:focus {
    box-shadow: none;
  }
}

.panel-footer {
  height: 64px;
  display: flex;
  align-items: center;
  padding: 0 20px;
  border-top: 1px solid #f0f0f0;
  background: #fafafa;
}

.ai-btn {
  width: 100%;
  height: 40px;
  font-weight: 600;
  letter-spacing: 1px;
  box-shadow: 0 4px 12px rgba(64, 158, 255, 0.3);
  transition: all 0.3s;
  
  &:hover {
    transform: translateY(-1px);
    box-shadow: 0 6px 16px rgba(64, 158, 255, 0.4);
  }
}

/* Right Panel Specifics */
.right-panel {
  width: 500px; /* Increased width */
  flex-shrink: 0;
  background: #f9fafc;
}

.episode-list-body {
  padding: 16px;
}

.episode-list {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.episode-card {
  background: #fff;
  border-radius: 10px;
  border: 1px solid #e4e7ed;
  padding: 20px; /* Increased padding */
  transition: all 0.3s;
  
  &:hover {
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
    border-color: #dcdfe6;
    transform: translateY(-2px);
  }
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 16px;
}

.episode-info {
  display: flex;
  align-items: center;
  gap: 8px;
}

.episode-num {
  background: #ecf5ff;
  color: #409EFF;
  font-size: 13px;
  font-weight: 700;
  padding: 4px 10px;
  border-radius: 4px;
}

.episode-name {
  font-weight: 600;
  color: #303133;
  font-size: 15px;
}

.el-dropdown-link {
  cursor: pointer;
  color: #909399;
  padding: 4px;
  
  &:hover {
    color: #409EFF;
  }
}

.card-content {
  margin-bottom: 16px;
}

.summary-label {
  font-size: 13px;
  color: #606266;
  margin-bottom: 8px;
  font-weight: 500;
}

.summary-input ::v-deep .el-textarea__inner {
  background: #f8f9fa;
  border-color: transparent;
  font-size: 14px;
  padding: 12px;
  
  &:focus {
    background: #fff;
    border-color: #409EFF;
  }
}

.card-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.action-btn {
  width: 100%;
  padding: 10px 20px;
}

.text-danger {
  color: #F56C6C;
}

/* Custom Scrollbar */
::-webkit-scrollbar {
  width: 6px;
  height: 6px;
}

::-webkit-scrollbar-track {
  background: transparent;
}

::-webkit-scrollbar-thumb {
  background: #dcdfe6;
  border-radius: 3px;
}

::-webkit-scrollbar-thumb:hover {
  background: #c0c4cc;
}
</style>