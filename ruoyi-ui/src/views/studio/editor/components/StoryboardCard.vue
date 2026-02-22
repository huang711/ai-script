<template>
  <el-card class="storyboard-card" shadow="never">
    <div class="card-body">
      <div class="shot-image">
        <div class="image-placeholder">16:9</div>
      </div>
      <div class="shot-form">
        <div class="shot-title">第 {{ storyboard.shotNum }} 镜</div>
        <el-form label-position="top" class="form">
          <el-form-item label="画面">
            <el-input
              type="textarea"
              :rows="3"
              v-model="storyboard.description"
              @blur="handleSave"
              @change="handleSave"
            />
          </el-form-item>
          <el-form-item label="对白">
            <el-input
              v-model="storyboard.dialogue"
              @blur="handleSave"
              @change="handleSave"
            />
          </el-form-item>
          <div class="form-row">
            <el-form-item label="运镜" class="form-item">
              <el-select
                v-model="storyboard.cameraMovement"
                placeholder="选择运镜"
                @change="handleSave"
              >
                <el-option label="推" value="推" />
                <el-option label="拉" value="拉" />
                <el-option label="摇" value="摇" />
                <el-option label="移" value="移" />
                <el-option label="跟" value="跟" />
                <el-option label="升" value="升" />
                <el-option label="降" value="降" />
              </el-select>
            </el-form-item>
            <el-form-item label="时长" class="form-item">
              <el-input
                v-model="storyboard.duration"
                @blur="handleSave"
                @change="handleSave"
              />
            </el-form-item>
          </div>
        </el-form>
      </div>
    </div>
  </el-card>
</template>

<script>
import { updateStoryboard } from '@/api/drama/editor'

export default {
  name: 'StoryboardCard',
  props: {
    storyboard: {
      type: Object,
      required: true
    }
  },
  methods: {
    handleSave() {
      if (!this.storyboard || !this.storyboard.id) {
        return
      }
      updateStoryboard(this.storyboard).then(() => {
        this.$emit('saved', this.storyboard)
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.storyboard-card {
  border: 1px solid #ebeef5;
  border-radius: 12px;
}

.card-body {
  display: flex;
  gap: 16px;
}

.shot-image {
  width: 240px;
  flex-shrink: 0;
}

.image-placeholder {
  width: 100%;
  padding-top: 56.25%;
  background: #f2f3f5;
  border-radius: 8px;
  position: relative;
  color: #909399;
  font-size: 14px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.shot-form {
  flex: 1;
}

.shot-title {
  font-size: 14px;
  font-weight: 600;
  margin-bottom: 8px;
  color: #303133;
}

.form-row {
  display: flex;
  gap: 12px;
}

.form-item {
  flex: 1;
}
</style>
