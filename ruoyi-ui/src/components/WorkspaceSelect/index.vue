<template>
  <el-select 
    v-model="workspaceId" 
    placeholder="切换空间" 
    size="small" 
    class="workspace-select"
    @change="handleWorkspaceChange"
  >
    <el-option
      v-for="item in workspaceList"
      :key="item.id"
      :label="item.name"
      :value="item.id"
    />
  </el-select>
</template>

<script>
import { listWorkspaces } from "@/api/workspace/workspaces";

export default {
  name: "WorkspaceSelect",
  data() {
    return {
      workspaceId: null,
      workspaceList: []
    };
  },
  created() {
    this.getWorkspaceList();
  },
  methods: {
    getWorkspaceList() {
      listWorkspaces().then(response => {
        this.workspaceList = response.rows;
        // 初始化逻辑
        const cachedId = localStorage.getItem('workspaceId');
        if (cachedId) {
          // 检查缓存ID是否在列表中 (可选，防止过期ID)
          const exists = this.workspaceList.some(w => w.id == cachedId);
          if (exists) {
            this.workspaceId = parseInt(cachedId);
          } else if (this.workspaceList.length > 0) {
             // 缓存无效但有列表，默认选中第一个
             this.selectWorkspace(this.workspaceList[0].id);
          }
        } else if (this.workspaceList.length > 0) {
          // 无缓存，默认选中第一个
          this.selectWorkspace(this.workspaceList[0].id);
        }
      });
    },
    handleWorkspaceChange(val) {
      this.selectWorkspace(val);
      // 刷新页面以应用新的 Header
      location.reload();
    },
    selectWorkspace(id) {
      this.workspaceId = id;
      localStorage.setItem('workspaceId', id);
    }
  }
};
</script>

<style scoped>
.workspace-select {
  width: 150px;
  margin-right: 10px;
}
</style>
