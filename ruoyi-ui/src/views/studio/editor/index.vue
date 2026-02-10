<template>
  <div class="studio-editor">
    <div class="editor-header">
      <h2>项目编辑器</h2>
      <p>当前项目 ID: {{ projectId }}</p>
    </div>
    <div class="editor-content">
      <el-empty description="编辑器功能开发中..."></el-empty>
    </div>
  </div>
</template>

<script>
export default {
  name: "StudioEditor",
  data() {
    return {
      projectId: null
    };
  },
  created() {
    this.projectId = this.$route.params.id;
  }
};
</script>

<style lang="scss" scoped>
.studio-editor {
  padding: 20px;
  background: #fff;
  min-height: calc(100vh - 100px);
  border-radius: 8px;
}

.editor-header {
  margin-bottom: 20px;
  border-bottom: 1px solid #eee;
  padding-bottom: 20px;
  
  h2 {
    margin: 0 0 10px 0;
  }
  
  p {
    color: #666;
    margin: 0;
  }
}
</style>