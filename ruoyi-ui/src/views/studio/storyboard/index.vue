<template>
  <div class="studio-storyboard">
    <div class="page-header">
      <div class="header-left">
        <el-button icon="el-icon-back" circle @click="goBack" class="back-btn" title="返回上一个页面"></el-button>
        <div class="title-section" v-if="currentEpisode">
          <span class="episode-tag">EP{{ currentEpisode.episodeNum }}</span>
          <span class="episode-title">{{ currentEpisode.title }}</span>
        </div>
      </div>
    </div>

    <div class="main-layout">
      <!-- Left Panel: Storyboard List -->
      <div class="left-panel" v-loading="loading">
        <el-empty v-if="!storyboardList.length && !loading" description="暂无分镜，请点击右侧测试按钮添加或生成"></el-empty>
        
        <div v-else class="storyboard-list">
          <storyboard-card
            v-for="shot in storyboardList"
            :key="shot.id"
            :storyboard="shot"
            class="storyboard-item"
            @saved="$message.success('分镜保存成功')"
          />
        </div>
      </div>

      <!-- Right Panel: Test Controls -->
      <div class="right-panel">
        <el-card class="control-card" header="测试控制台">
          <div class="control-group">
            <div class="group-title">基础操作</div>
            <el-button type="primary" size="small" icon="el-icon-plus" @click="testAddStoryboard">添加测试分镜</el-button>
            <el-button type="success" size="small" icon="el-icon-refresh" @click="loadStoryboards">刷新列表</el-button>
            <el-button type="danger" size="small" icon="el-icon-delete" @click="testDeleteAll">清空分镜(Test)</el-button>
          </div>

          <div class="control-group">
            <div class="group-title">AI 生成</div>
            <el-button 
              type="warning" 
              size="small" 
              icon="el-icon-magic-stick" 
              :loading="generating" 
              @click="handleGenerateStoryboards"
            >
              AI 生成分镜
            </el-button>
          </div>

          <div class="control-group">
            <div class="group-title">数据概览</div>
            <div class="info-item">当前分集ID: {{ episodeId }}</div>
            <div class="info-item">分镜数量: {{ storyboardList.length }}</div>
          </div>
        </el-card>
      </div>
    </div>
  </div>
</template>

<script>
import { listStoryboardsByEpisodeId, generateStoryboards } from '@/api/drama/editor'
import { getEpisodes } from '@/api/drama/episodes'
import { addStoryboards, delStoryboards } from '@/api/drama/storyboards'
import StoryboardCard from '../editor/components/StoryboardCard'

export default {
  name: 'StudioStoryboard',
  components: { StoryboardCard },
  data() {
    return {
      episodeId: null,
      currentEpisode: null,
      storyboardList: [],
      loading: false,
      generating: false
    }
  },
  created() {
    this.episodeId = this.$route.params.episodeId
    this.initData()
  },
  methods: {
    async initData() {
      if (!this.episodeId) {
        this.$message.error('参数错误')
        return
      }
      this.loading = true
      try {
        await this.loadEpisodeInfo()
        await this.loadStoryboards()
      } finally {
        this.loading = false
      }
    },
    async loadEpisodeInfo() {
      try {
        const res = await getEpisodes(this.episodeId)
        if (res.data) {
          this.currentEpisode = res.data
        }
      } catch (error) {
        console.error('Failed to load episode info', error)
      }
    },
    async loadStoryboards() {
      const res = await listStoryboardsByEpisodeId(this.episodeId)
      this.storyboardList = res.rows || []
    },
    async handleGenerateStoryboards() {
      this.generating = true
      try {
        await generateStoryboards(this.episodeId)
        await this.loadStoryboards()
        this.$message.success('AI 分镜生成完成')
      } catch (error) {
        this.$message.error('生成失败')
      } finally {
        this.generating = false
      }
    },
    async testAddStoryboard() {
      try {
        const nextShot = this.storyboardList.length + 1
        await addStoryboards({
          episodeId: this.episodeId,
          shotNum: nextShot,
          description: `这是第 ${nextShot} 个分镜的画面描述。`,
          dialogue: '这里是对白...',
          cameraMovement: '固定',
          duration: '3s'
        })
        this.$message.success('添加测试分镜成功')
        this.loadStoryboards()
      } catch (error) {
        this.$message.error('添加失败: ' + error.message)
      }
    },
    async testDeleteAll() {
      try {
        await this.$confirm('确定要清空当前所有分镜吗？', '警告', { type: 'warning' })
        const ids = this.storyboardList.map(s => s.id)
        if (ids.length === 0) return
        
        // Batch delete if API supports it, otherwise loop (API likely supports comma separated string for @PathVariable array)
        // Check API: `url: '/drama/storyboards/' + id` (ScriptsController uses `/{ids}`)
        // Assuming StoryboardsController uses `/{ids}` for remove like standard RuoYi
        
        await delStoryboards(ids.join(','))
        this.$message.success('清空成功')
        this.loadStoryboards()
      } catch (error) {
        if (error !== 'cancel') {
           this.$message.error('删除失败')
        }
      }
    },
    goBack() {
      const projectId = this.$route.query.projectId
      if (projectId) {
        this.$router.push({ name: 'StudioEditor', params: { id: projectId } })
      } else {
        this.$router.go(-1)
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.studio-storyboard {
  height: calc(100vh - 50px);
  background: #f0f2f5;
  padding: 16px;
  display: flex;
  flex-direction: column;
}

.page-header {
  background: #fff;
  padding: 12px 24px;
  border-radius: 8px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 16px;
  flex-shrink: 0;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.05);
}

.header-left {
  display: flex;
  align-items: center;
  gap: 16px;
}

.back-btn {
  border: none;
  background: #f5f7fa;
  &:hover {
    background: #e4e7ed;
    color: #409EFF;
  }
}

.title-section {
  display: flex;
  align-items: center;
  gap: 12px;
}

.episode-tag {
  background: #ecf5ff;
  color: #409EFF;
  font-weight: 700;
  padding: 4px 10px;
  border-radius: 6px;
  font-size: 14px;
}

.episode-title {
  font-size: 18px;
  font-weight: 600;
  color: #303133;
}

.main-layout {
  flex: 1;
  display: flex;
  gap: 16px;
  min-height: 0; /* Important for scrolling */
}

.left-panel {
  flex: 1;
  overflow-y: auto;
  padding-right: 8px;
  
  /* Hide scrollbar for cleaner look */
  &::-webkit-scrollbar {
    width: 6px;
  }
  &::-webkit-scrollbar-thumb {
    background: #dcdfe6;
    border-radius: 3px;
  }
}

.storyboard-list {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.right-panel {
  width: 320px;
  flex-shrink: 0;
}

.control-card {
  position: sticky;
  top: 0;
}

.control-group {
  margin-bottom: 24px;
  display: flex;
  flex-direction: column;
  gap: 12px;
  
  &:last-child {
    margin-bottom: 0;
  }
}

.group-title {
  font-size: 14px;
  font-weight: 600;
  color: #606266;
  margin-bottom: 8px;
  padding-left: 8px;
  border-left: 3px solid #409EFF;
}

.info-item {
  font-size: 13px;
  color: #909399;
  padding: 4px 8px;
  background: #f5f7fa;
  border-radius: 4px;
}
</style>
