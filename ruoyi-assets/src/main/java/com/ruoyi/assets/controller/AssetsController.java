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
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.assets.domain.Assets;
import com.ruoyi.assets.service.IAssetsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 素材资产Controller
 * 
 * @author wukai
 * @date 2026-02-10
 */
@RestController
@RequestMapping("/assets/assets")
public class AssetsController extends BaseController
{
    @Autowired
    private IAssetsService assetsService;

    /**
     * 查询素材资产列表
     */
    @PreAuthorize("@ss.hasPermi('assets:assets:list')")
    @GetMapping("/list")
    public TableDataInfo list(Assets assets)
    {
        assets.setWorkspaceId(WorkspaceContextHolder.getWorkspaceId());
        startPage();
        List<Assets> list = assetsService.selectAssetsList(assets);
        return getDataTable(list);
    }

    /**
     * 导出素材资产列表
     */
    @PreAuthorize("@ss.hasPermi('assets:assets:export')")
    @Log(title = "素材资产", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Assets assets)
    {
        List<Assets> list = assetsService.selectAssetsList(assets);
        ExcelUtil<Assets> util = new ExcelUtil<Assets>(Assets.class);
        util.exportExcel(response, list, "素材资产数据");
    }

    /**
     * 获取素材资产详细信息
     */
    @PreAuthorize("@ss.hasPermi('assets:assets:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(assetsService.selectAssetsById(id));
    }

    /**
     * 新增素材资产
     */
    @PreAuthorize("@ss.hasPermi('assets:assets:add')")
    @Log(title = "素材资产", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Assets assets)
    {
        assets.setWorkspaceId(WorkspaceContextHolder.getWorkspaceId());
        return toAjax(assetsService.insertAssets(assets));
    }

    /**
     * 修改素材资产
     */
    @PreAuthorize("@ss.hasPermi('assets:assets:edit')")
    @Log(title = "素材资产", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Assets assets)
    {
        return toAjax(assetsService.updateAssets(assets));
    }

    /**
     * 删除素材资产
     */
    @PreAuthorize("@ss.hasPermi('assets:assets:remove')")
    @Log(title = "素材资产", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(assetsService.deleteAssetsByIds(ids));
    }
}
