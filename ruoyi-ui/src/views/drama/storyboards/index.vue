<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="剧集编号" prop="episodeId">
        <el-input
          v-model="queryParams.episodeId"
          placeholder="请输入剧集编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="镜号" prop="shotNum">
        <el-input
          v-model="queryParams.shotNum"
          placeholder="请输入镜号"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['drama:storyboards:add']"
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
          v-hasPermi="['drama:storyboards:edit']"
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
          v-hasPermi="['drama:storyboards:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['drama:storyboards:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="storyboardsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="镜号" align="center" prop="shotNum" />
      <el-table-column label="画面描述" align="center" prop="description" />
      <el-table-column label="运镜" align="center" prop="cameraMovement">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.drama_camera_move" :value="scope.row.cameraMovement"/>
        </template>
      </el-table-column>
      <el-table-column label="时长" align="center" prop="duration" />
      <el-table-column label="图像文件ID" align="center" prop="imageFileId" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.imageFileId" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['drama:storyboards:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['drama:storyboards:remove']"
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

    <!-- 添加或修改分镜创作对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="剧集编号" prop="episodeId">
          <el-input v-model="form.episodeId" placeholder="请输入剧集编号" />
        </el-form-item>
        <el-form-item label="镜号" prop="shotNum">
          <el-input v-model="form.shotNum" placeholder="请输入镜号" />
        </el-form-item>
        <el-form-item label="画面描述" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="对白" prop="dialogue">
          <el-input v-model="form.dialogue" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="运镜" prop="cameraMovement">
          <el-select v-model="form.cameraMovement" placeholder="请选择运镜">
            <el-option
              v-for="dict in dict.type.drama_camera_move"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="时长" prop="duration">
          <el-input v-model="form.duration" placeholder="请输入时长" />
        </el-form-item>
        <el-form-item label="图像文件ID" prop="imageFileId">
          <image-upload v-model="form.imageFileId"/>
        </el-form-item>
        <el-form-item label="视频文件ID" prop="videoFileId">
          <file-upload v-model="form.videoFileId"/>
        </el-form-item>
        <el-form-item label="音频文件ID" prop="audioFileId">
          <file-upload v-model="form.audioFileId"/>
        </el-form-item>
        <el-form-item label="AI提示词" prop="prompt">
          <el-input v-model="form.prompt" type="textarea" placeholder="请输入内容" />
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
import { listStoryboards, getStoryboards, delStoryboards, addStoryboards, updateStoryboards } from "@/api/drama/storyboards"

export default {
  name: "Storyboards",
  dicts: ['drama_camera_move'],
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
      // 分镜创作表格数据
      storyboardsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        episodeId: null,
        shotNum: null,
        description: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        episodeId: [
          { required: true, message: "剧集编号不能为空", trigger: "blur" }
        ],
        shotNum: [
          { required: true, message: "镜号不能为空", trigger: "blur" }
        ],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询分镜创作列表 */
    getList() {
      this.loading = true
      listStoryboards(this.queryParams).then(response => {
        this.storyboardsList = response.rows
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
        episodeId: null,
        shotNum: null,
        description: null,
        dialogue: null,
        cameraMovement: null,
        duration: null,
        imageFileId: null,
        videoFileId: null,
        audioFileId: null,
        prompt: null,
        aiParams: null,
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
      this.title = "添加分镜创作"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getStoryboards(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改分镜创作"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateStoryboards(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addStoryboards(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除分镜创作编号为"' + ids + '"的数据项？').then(function() {
        return delStoryboards(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('drama/storyboards/export', {
        ...this.queryParams
      }, `storyboards_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
