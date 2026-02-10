<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="ID" prop="id">
        <el-input
          v-model="queryParams.id"
          placeholder="请输入ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="空间ID" prop="workspaceId">
        <el-input
          v-model="queryParams.workspaceId"
          placeholder="请输入空间ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上传者ID" prop="uploaderId">
        <el-input
          v-model="queryParams.uploaderId"
          placeholder="请输入上传者ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="OSS Key" prop="fileKey">
        <el-input
          v-model="queryParams.fileKey"
          placeholder="请输入OSS Key"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="文件名" prop="originalName">
        <el-input
          v-model="queryParams.originalName"
          placeholder="请输入文件名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="类型" prop="mimeType">
        <el-select v-model="queryParams.mimeType" placeholder="请选择类型" clearable>
          <el-option
            v-for="dict in dict.type.sys_asset_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="大小" prop="sizeBytes">
        <el-input
          v-model="queryParams.sizeBytes"
          placeholder="请输入大小"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="供应商" prop="provider">
        <el-select v-model="queryParams.provider" placeholder="请选择供应商" clearable>
          <el-option
            v-for="dict in dict.type.ai_provider"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="daterangeCreateTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['assets:files:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['assets:files:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['assets:files:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['assets:files:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="filesList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="空间ID" align="center" prop="workspaceId" />
      <el-table-column label="上传者ID" align="center" prop="uploaderId" />
      <el-table-column label="OSS Key" align="center" prop="fileKey" />
      <el-table-column label="文件名" align="center" prop="originalName" />
      <el-table-column label="类型" align="center" prop="mimeType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_asset_type" :value="scope.row.mimeType"/>
        </template>
      </el-table-column>
      <el-table-column label="大小" align="center" prop="sizeBytes" />
      <el-table-column label="供应商" align="center" prop="provider">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.ai_provider" :value="scope.row.provider"/>
        </template>
      </el-table-column>
      <el-table-column label="CDN链接" align="center" prop="cdnUrl" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['assets:files:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['assets:files:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改文件存储对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="空间ID" prop="workspaceId">
          <el-input v-model="form.workspaceId" placeholder="请输入空间ID" />
        </el-form-item>
        <el-form-item label="上传者ID" prop="uploaderId">
          <el-input v-model="form.uploaderId" placeholder="请输入上传者ID" />
        </el-form-item>
        <el-form-item label="OSS Key" prop="fileKey">
          <el-input v-model="form.fileKey" placeholder="请输入OSS Key" />
        </el-form-item>
        <el-form-item label="文件名" prop="originalName">
          <el-input v-model="form.originalName" placeholder="请输入文件名" />
        </el-form-item>
        <el-form-item label="类型" prop="mimeType">
          <el-select v-model="form.mimeType" placeholder="请选择类型">
            <el-option
              v-for="dict in dict.type.sys_asset_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="供应商" prop="provider">
          <el-select v-model="form.provider" placeholder="请选择供应商">
            <el-option
              v-for="dict in dict.type.ai_provider"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="CDN链接" prop="cdnUrl">
          <el-input v-model="form.cdnUrl" type="textarea" placeholder="请输入内容" />
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
import { listFiles, getFiles, delFiles, addFiles, updateFiles } from "@/api/assets/files"

export default {
  name: "Files",
  dicts: ['ai_provider', 'sys_asset_type'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 文件存储表格数据
      filesList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // CDN链接时间范围
      daterangeCreateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        workspaceId: null,
        uploaderId: null,
        fileKey: null,
        originalName: null,
        mimeType: null,
        sizeBytes: null,
        provider: null,
        cdnUrl: null,
        createTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        workspaceId: [
          { required: true, message: "空间ID不能为空", trigger: "blur" }
        ],
        uploaderId: [
          { required: true, message: "上传者ID不能为空", trigger: "blur" }
        ],
        fileKey: [
          { required: true, message: "OSS Key不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询文件存储列表 */
    getList() {
      this.loading = true
      this.queryParams.params = {}
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params["beginCreateTime"] = this.daterangeCreateTime[0]
        this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1]
      }
      listFiles(this.queryParams).then(response => {
        this.filesList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        workspaceId: null,
        uploaderId: null,
        fileKey: null,
        originalName: null,
        mimeType: null,
        sizeBytes: null,
        provider: null,
        cdnUrl: null,
        createTime: null
      }
      this.resetForm("form")
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeCreateTime = []
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加文件存储"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getFiles(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改文件存储"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateFiles(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addFiles(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$modal.confirm('是否确认删除文件存储编号为"' + ids + '"的数据项？').then(function() {
        return delFiles(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('assets/files/export', {
        ...this.queryParams
      }, `files_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
