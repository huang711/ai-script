package com.ruoyi.assets.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.context.WorkspaceContextHolder;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.assets.domain.StorageFiles;
import com.ruoyi.assets.service.IStorageFilesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文件存储Controller
 * 
 * @author ruoyi
 * @date 2026-02-10
 */
@RestController
@RequestMapping("/assets/files")
public class StorageFilesController extends BaseController
{
    @Autowired
    private IStorageFilesService storageFilesService;

    /**
     * 查询文件存储列表
     */
    @PreAuthorize("@ss.hasPermi('assets:files:list')")
    @GetMapping("/list")
    public TableDataInfo list(StorageFiles storageFiles)
    {
        storageFiles.setWorkspaceId(WorkspaceContextHolder.getWorkspaceId());
        startPage();
        List<StorageFiles> list = storageFilesService.selectStorageFilesList(storageFiles);
        return getDataTable(list);
    }

    /**
     * 导出文件存储列表
     */
    @PreAuthorize("@ss.hasPermi('assets:files:export')")
    @Log(title = "文件存储", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StorageFiles storageFiles)
    {
        List<StorageFiles> list = storageFilesService.selectStorageFilesList(storageFiles);
        ExcelUtil<StorageFiles> util = new ExcelUtil<StorageFiles>(StorageFiles.class);
        util.exportExcel(response, list, "文件存储数据");
    }

    /**
     * 获取文件存储详细信息
     */
    @PreAuthorize("@ss.hasPermi('assets:files:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(storageFilesService.selectStorageFilesById(id));
    }

    /**
     * 新增文件存储
     */
    @PreAuthorize("@ss.hasPermi('assets:files:add')")
    @Log(title = "文件存储", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StorageFiles storageFiles)
    {
        storageFiles.setWorkspaceId(WorkspaceContextHolder.getWorkspaceId());
        storageFiles.setUploaderId(SecurityUtils.getUserId());
        return toAjax(storageFilesService.insertStorageFiles(storageFiles));
    }

    /**
     * 修改文件存储
     */
    @PreAuthorize("@ss.hasPermi('assets:files:edit')")
    @Log(title = "文件存储", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StorageFiles storageFiles)
    {
        return toAjax(storageFilesService.updateStorageFiles(storageFiles));
    }

    /**
     * 删除文件存储
     */
    @PreAuthorize("@ss.hasPermi('assets:files:remove')")
    @Log(title = "文件存储", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(storageFilesService.deleteStorageFilesByIds(ids));
    }
}
