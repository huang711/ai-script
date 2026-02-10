package com.ruoyi.drama.controller;

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
import com.ruoyi.drama.domain.StoryboardAsset;
import com.ruoyi.drama.service.IStoryboardAssetService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 分镜资产引用Controller
 * 
 * @author ruoyi
 * @date 2026-02-10
 */
@RestController
@RequestMapping("/drama/storyboardAsset")
public class StoryboardAssetController extends BaseController
{
    @Autowired
    private IStoryboardAssetService storyboardAssetService;

    /**
     * 查询分镜资产引用列表
     */
    @PreAuthorize("@ss.hasPermi('drama:storyboardAsset:list')")
    @GetMapping("/list")
    public TableDataInfo list(StoryboardAsset storyboardAsset)
    {
        startPage();
        List<StoryboardAsset> list = storyboardAssetService.selectStoryboardAssetList(storyboardAsset);
        return getDataTable(list);
    }

    /**
     * 导出分镜资产引用列表
     */
    @PreAuthorize("@ss.hasPermi('drama:storyboardAsset:export')")
    @Log(title = "分镜资产引用", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StoryboardAsset storyboardAsset)
    {
        List<StoryboardAsset> list = storyboardAssetService.selectStoryboardAssetList(storyboardAsset);
        ExcelUtil<StoryboardAsset> util = new ExcelUtil<StoryboardAsset>(StoryboardAsset.class);
        util.exportExcel(response, list, "分镜资产引用数据");
    }

    /**
     * 获取分镜资产引用详细信息
     */
    @PreAuthorize("@ss.hasPermi('drama:storyboardAsset:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(storyboardAssetService.selectStoryboardAssetById(id));
    }

    /**
     * 新增分镜资产引用
     */
    @PreAuthorize("@ss.hasPermi('drama:storyboardAsset:add')")
    @Log(title = "分镜资产引用", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StoryboardAsset storyboardAsset)
    {
        return toAjax(storyboardAssetService.insertStoryboardAsset(storyboardAsset));
    }

    /**
     * 修改分镜资产引用
     */
    @PreAuthorize("@ss.hasPermi('drama:storyboardAsset:edit')")
    @Log(title = "分镜资产引用", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StoryboardAsset storyboardAsset)
    {
        return toAjax(storyboardAssetService.updateStoryboardAsset(storyboardAsset));
    }

    /**
     * 删除分镜资产引用
     */
    @PreAuthorize("@ss.hasPermi('drama:storyboardAsset:remove')")
    @Log(title = "分镜资产引用", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(storyboardAssetService.deleteStoryboardAssetByIds(ids));
    }
}
