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
import com.ruoyi.drama.domain.Scripts;
import com.ruoyi.drama.service.IScriptsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 剧本管理Controller
 * 
 * @author wukai
 * @date 2026-02-10
 */
@RestController
@RequestMapping("/drama/scripts")
public class ScriptsController extends BaseController
{
    @Autowired
    private IScriptsService scriptsService;

    /**
     * 查询剧本管理列表
     */
    @PreAuthorize("@ss.hasPermi('drama:scripts:list')")
    @GetMapping("/list")
    public TableDataInfo list(Scripts scripts)
    {
        startPage();
        List<Scripts> list = scriptsService.selectScriptsList(scripts);
        return getDataTable(list);
    }

    /**
     * 导出剧本管理列表
     */
    @PreAuthorize("@ss.hasPermi('drama:scripts:export')")
    @Log(title = "剧本管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Scripts scripts)
    {
        List<Scripts> list = scriptsService.selectScriptsList(scripts);
        ExcelUtil<Scripts> util = new ExcelUtil<Scripts>(Scripts.class);
        util.exportExcel(response, list, "剧本管理数据");
    }

    /**
     * 获取剧本管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('drama:scripts:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(scriptsService.selectScriptsById(id));
    }

    /**
     * 新增剧本管理
     */
    @PreAuthorize("@ss.hasPermi('drama:scripts:add')")
    @Log(title = "剧本管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Scripts scripts)
    {
        return toAjax(scriptsService.insertScripts(scripts));
    }

    /**
     * 修改剧本管理
     */
    @PreAuthorize("@ss.hasPermi('drama:scripts:edit')")
    @Log(title = "剧本管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Scripts scripts)
    {
        return toAjax(scriptsService.updateScripts(scripts));
    }

    /**
     * 删除剧本管理
     */
    @PreAuthorize("@ss.hasPermi('drama:scripts:remove')")
    @Log(title = "剧本管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(scriptsService.deleteScriptsByIds(ids));
    }
}
