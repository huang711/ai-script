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
import com.ruoyi.workspace.domain.WorkspaceMembers;
import com.ruoyi.workspace.service.IWorkspaceMembersService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 空间成员Controller
 * 
 * @author wukai
 * @date 2026-02-10
 */
@RestController
@RequestMapping("/workspace/member")
public class WorkspaceMembersController extends BaseController
{
    @Autowired
    private IWorkspaceMembersService workspaceMembersService;

    /**
     * 查询空间成员列表
     */
    @PreAuthorize("@ss.hasPermi('workspace:member:list')")
    @GetMapping("/list")
    public TableDataInfo list(WorkspaceMembers workspaceMembers)
    {
        startPage();
        List<WorkspaceMembers> list = workspaceMembersService.selectWorkspaceMembersList(workspaceMembers);
        return getDataTable(list);
    }

    /**
     * 导出空间成员列表
     */
    @PreAuthorize("@ss.hasPermi('workspace:member:export')")
    @Log(title = "空间成员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WorkspaceMembers workspaceMembers)
    {
        List<WorkspaceMembers> list = workspaceMembersService.selectWorkspaceMembersList(workspaceMembers);
        ExcelUtil<WorkspaceMembers> util = new ExcelUtil<WorkspaceMembers>(WorkspaceMembers.class);
        util.exportExcel(response, list, "空间成员数据");
    }

    /**
     * 获取空间成员详细信息
     */
    @PreAuthorize("@ss.hasPermi('workspace:member:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(workspaceMembersService.selectWorkspaceMembersById(id));
    }

    /**
     * 新增空间成员
     */
    @PreAuthorize("@ss.hasPermi('workspace:member:add')")
    @Log(title = "空间成员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WorkspaceMembers workspaceMembers)
    {
        return toAjax(workspaceMembersService.insertWorkspaceMembers(workspaceMembers));
    }

    /**
     * 修改空间成员
     */
    @PreAuthorize("@ss.hasPermi('workspace:member:edit')")
    @Log(title = "空间成员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WorkspaceMembers workspaceMembers)
    {
        return toAjax(workspaceMembersService.updateWorkspaceMembers(workspaceMembers));
    }

    /**
     * 删除空间成员
     */
    @PreAuthorize("@ss.hasPermi('workspace:member:remove')")
    @Log(title = "空间成员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(workspaceMembersService.deleteWorkspaceMembersByIds(ids));
    }
}
