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
import com.ruoyi.drama.domain.Episodes;
import com.ruoyi.drama.service.IEpisodesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 分集管理Controller
 * 
 * @author wukai
 * @date 2026-02-10
 */
@RestController
@RequestMapping("/drama/episodes")
public class EpisodesController extends BaseController
{
    @Autowired
    private IEpisodesService episodesService;

    /**
     * 查询分集管理列表
     */
    @PreAuthorize("@ss.hasPermi('drama:episodes:list')")
    @GetMapping("/list")
    public TableDataInfo list(Episodes episodes)
    {
        startPage();
        List<Episodes> list = episodesService.selectEpisodesList(episodes);
        return getDataTable(list);
    }

    /**
     * 导出分集管理列表
     */
    @PreAuthorize("@ss.hasPermi('drama:episodes:export')")
    @Log(title = "分集管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Episodes episodes)
    {
        List<Episodes> list = episodesService.selectEpisodesList(episodes);
        ExcelUtil<Episodes> util = new ExcelUtil<Episodes>(Episodes.class);
        util.exportExcel(response, list, "分集管理数据");
    }

    /**
     * 获取分集管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('drama:episodes:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(episodesService.selectEpisodesById(id));
    }

    /**
     * 新增分集管理
     */
    @PreAuthorize("@ss.hasPermi('drama:episodes:add')")
    @Log(title = "分集管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Episodes episodes)
    {
        return toAjax(episodesService.insertEpisodes(episodes));
    }

    /**
     * 修改分集管理
     */
    @PreAuthorize("@ss.hasPermi('drama:episodes:edit')")
    @Log(title = "分集管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Episodes episodes)
    {
        return toAjax(episodesService.updateEpisodes(episodes));
    }

    /**
     * 删除分集管理
     */
    @PreAuthorize("@ss.hasPermi('drama:episodes:remove')")
    @Log(title = "分集管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(episodesService.deleteEpisodesByIds(ids));
    }
}
