package com.ruoyi.web.controller;

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
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.StorageFiles;
import com.ruoyi.system.service.IStorageFilesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 物理文件Controller
 * 
 * @author ruoyi
 * @date 2026-02-03
 */
@RestController
@RequestMapping("/system/files")
public class StorageFilesController extends BaseController
{
    @Autowired
    private IStorageFilesService storageFilesService;

    /**
     * 查询物理文件列表
     */
    @PreAuthorize("@ss.hasPermi('system:files:list')")
    @GetMapping("/list")
    public TableDataInfo list(StorageFiles storageFiles)
    {
        startPage();
        List<StorageFiles> list = storageFilesService.selectStorageFilesList(storageFiles);
        return getDataTable(list);
    }

    /**
     * 导出物理文件列表
     */
    @PreAuthorize("@ss.hasPermi('system:files:export')")
    @Log(title = "物理文件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StorageFiles storageFiles)
    {
        List<StorageFiles> list = storageFilesService.selectStorageFilesList(storageFiles);
        ExcelUtil<StorageFiles> util = new ExcelUtil<StorageFiles>(StorageFiles.class);
        util.exportExcel(response, list, "物理文件数据");
    }

    /**
     * 获取物理文件详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:files:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(storageFilesService.selectStorageFilesById(id));
    }

    /**
     * 新增物理文件
     */
    @PreAuthorize("@ss.hasPermi('system:files:add')")
    @Log(title = "物理文件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StorageFiles storageFiles)
    {
        return toAjax(storageFilesService.insertStorageFiles(storageFiles));
    }

    /**
     * 修改物理文件
     */
    @PreAuthorize("@ss.hasPermi('system:files:edit')")
    @Log(title = "物理文件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StorageFiles storageFiles)
    {
        return toAjax(storageFilesService.updateStorageFiles(storageFiles));
    }

    /**
     * 删除物理文件
     */
    @PreAuthorize("@ss.hasPermi('system:files:remove')")
    @Log(title = "物理文件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(storageFilesService.deleteStorageFilesByIds(ids));
    }
}
