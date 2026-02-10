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
import com.ruoyi.drama.domain.Storyboards;
import com.ruoyi.drama.service.IStoryboardsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 分镜创作Controller
 * 
 * @author wukai
 * @date 2026-02-10
 */
@RestController
@RequestMapping("/drama/storyboards")
public class StoryboardsController extends BaseController
{
    @Autowired
    private IStoryboardsService storyboardsService;

    /**
     * 查询分镜创作列表
     */
    @PreAuthorize("@ss.hasPermi('drama:storyboards:list')")
    @GetMapping("/list")
    public TableDataInfo list(Storyboards storyboards)
    {
        startPage();
        List<Storyboards> list = storyboardsService.selectStoryboardsList(storyboards);
        return getDataTable(list);
    }

    /**
     * 导出分镜创作列表
     */
    @PreAuthorize("@ss.hasPermi('drama:storyboards:export')")
    @Log(title = "分镜创作", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Storyboards storyboards)
    {
        List<Storyboards> list = storyboardsService.selectStoryboardsList(storyboards);
        ExcelUtil<Storyboards> util = new ExcelUtil<Storyboards>(Storyboards.class);
        util.exportExcel(response, list, "分镜创作数据");
    }

    /**
     * 获取分镜创作详细信息
     */
    @PreAuthorize("@ss.hasPermi('drama:storyboards:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(storyboardsService.selectStoryboardsById(id));
    }

    /**
     * 新增分镜创作
     */
    @PreAuthorize("@ss.hasPermi('drama:storyboards:add')")
    @Log(title = "分镜创作", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Storyboards storyboards)
    {
        return toAjax(storyboardsService.insertStoryboards(storyboards));
    }

    /**
     * 修改分镜创作
     */
    @PreAuthorize("@ss.hasPermi('drama:storyboards:edit')")
    @Log(title = "分镜创作", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Storyboards storyboards)
    {
        return toAjax(storyboardsService.updateStoryboards(storyboards));
    }

    /**
     * 删除分镜创作
     */
    @PreAuthorize("@ss.hasPermi('drama:storyboards:remove')")
    @Log(title = "分镜创作", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(storyboardsService.deleteStoryboardsByIds(ids));
    }
}
