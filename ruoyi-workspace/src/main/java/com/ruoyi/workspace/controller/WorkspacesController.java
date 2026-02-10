package com.ruoyi.workspace.controller;

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
import com.ruoyi.workspace.domain.Workspaces;
import com.ruoyi.workspace.service.IWorkspacesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.SecurityUtils;

/**
 * 工作空间Controller
 * 
 * @author wukai
 * @date 2026-02-08
 */
@RestController
@RequestMapping("/workspace/workspaces")
public class WorkspacesController extends BaseController
{
    @Autowired
    private IWorkspacesService workspacesService;

    /**
     * 查询工作空间列表
     */
    @PreAuthorize("@ss.hasPermi('workspace:workspaces:list')")
    @GetMapping("/list")
    public TableDataInfo list(Workspaces workspaces)
    {
        startPage();
        List<Workspaces> list = workspacesService.selectWorkspacesList(workspaces);
        return getDataTable(list);
    }

    /**
     * 导出工作空间列表
     */
    @PreAuthorize("@ss.hasPermi('workspace:workspaces:export')")
    @Log(title = "工作空间", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Workspaces workspaces)
    {
        List<Workspaces> list = workspacesService.selectWorkspacesList(workspaces);
        ExcelUtil<Workspaces> util = new ExcelUtil<Workspaces>(Workspaces.class);
        util.exportExcel(response, list, "工作空间数据");
    }

    /**
     * 获取工作空间详细信息
     */
    @PreAuthorize("@ss.hasPermi('workspace:workspaces:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(workspacesService.selectWorkspacesById(id));
    }

    /**
     * 新增工作空间
     */
    @PreAuthorize("@ss.hasPermi('workspace:workspaces:add')")
    @Log(title = "工作空间", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Workspaces workspaces)
    {
        workspaces.setOwnerId(SecurityUtils.getUserId());
        return toAjax(workspacesService.insertWorkspaces(workspaces));
    }

    /**
     * 修改工作空间
     */
    @PreAuthorize("@ss.hasPermi('workspace:workspaces:edit')")
    @Log(title = "工作空间", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Workspaces workspaces)
    {
        return toAjax(workspacesService.updateWorkspaces(workspaces));
    }

    /**
     * 删除工作空间
     */
    @PreAuthorize("@ss.hasPermi('workspace:workspaces:remove')")
    @Log(title = "工作空间", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(workspacesService.deleteWorkspacesByIds(ids));
    }
}
