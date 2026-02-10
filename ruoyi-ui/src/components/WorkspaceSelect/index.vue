<template>
  <div class="workspace-select">
    <el-select 
      v-model="currentWorkspace" 
      placeholder="切换空间" 
      size="small" 
      style="width: 150px"
      @change="handleChange"
    >
      <el-option
        v-for="item in workspaceList"
        :key="item.id"
        :label="item.name"
        :value="item.id"
      />
    </el-select>
  </div>
</template>

<script>
import { listWorkspaces } from "@/api/workspace/workspaces";

export default {
  name: "WorkspaceSelect",
  data() {
    return {
      currentWorkspace: null,
      workspaceList: []
    };
  },
  created() {
    this.init();
  },
  methods: {
    init() {
      listWorkspaces().then(response => {
        this.workspaceList = response.rows;
        const savedId = localStorage.getItem("workspaceId");
        
        if (savedId && this.workspaceList.some(w => w.id == savedId)) {
          this.currentWorkspace = parseInt(savedId);
        } else if (this.workspaceList.length > 0) {
          // Default to first one
          this.currentWorkspace = this.workspaceList[0].id;
          localStorage.setItem("workspaceId", this.currentWorkspace);
        }
      });
    },
    handleChange(val) {
      localStorage.setItem("workspaceId", val);
      location.reload();
    }
  }
};
</script>

<style scoped>
.workspace-select {
  display: inline-block;
  vertical-align: text-bottom;
  margin-right: 20px;
}
</style>
