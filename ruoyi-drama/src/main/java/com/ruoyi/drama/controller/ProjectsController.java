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
import com.ruoyi.drama.domain.Projects;
import com.ruoyi.drama.service.IProjectsService;
import com.ruoyi.common.core.context.WorkspaceContextHolder;
import com.ruoyi.drama.domain.ProjectAsset;
import com.ruoyi.drama.service.IProjectAssetService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目管理Controller
 * 
 * @author wukai
 * @date 2026-02-10
 */
@RestController
@RequestMapping("/drama/projects")
public class ProjectsController extends BaseController
{
    @Autowired
    private IProjectsService projectsService;
    
    @Autowired
    private IProjectAssetService projectAssetService;

    /**
     * 查询项目管理列表
     */
    @PreAuthorize("@ss.hasPermi('drama:projects:list')")
    @GetMapping("/list")
    public TableDataInfo list(Projects projects)
    {
        startPage();
        List<Projects> list = projectsService.selectProjectsList(projects);
        return getDataTable(list);
    }
    
    @PreAuthorize("@ss.hasPermi('drama:projects:add')")
    @Log(title = "项目资产关联", businessType = BusinessType.INSERT)
    @PostMapping("/assets/add")
    public AjaxResult addAsset(@RequestBody ProjectAsset projectAsset)
    {
        return toAjax(projectAssetService.insertProjectAsset(projectAsset));
    }

    /**
     * 导出项目管理列表
     */
    @PreAuthorize("@ss.hasPermi('drama:projects:export')")
    @Log(title = "项目管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Projects projects)
    {
        projects.setWorkspaceId(WorkspaceContextHolder.getWorkspaceId());
        List<Projects> list = projectsService.selectProjectsList(projects);
        ExcelUtil<Projects> util = new ExcelUtil<Projects>(Projects.class);
        util.exportExcel(response, list, "项目管理数据");
    }

    /**
     * 获取项目管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('drama:projects:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(projectsService.selectProjectsById(id));
    }

    /**
     * 新增项目管理
     */
    @PreAuthorize("@ss.hasPermi('drama:projects:add')")
    @Log(title = "项目管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Projects projects)
    {
        return toAjax(projectsService.insertProjects(projects));
    }

    /**
     * 修改项目管理
     */
    @PreAuthorize("@ss.hasPermi('drama:projects:edit')")
    @Log(title = "项目管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Projects projects)
    {
        return toAjax(projectsService.updateProjects(projects));
    }

    /**
     * 删除项目管理
     */
    @PreAuthorize("@ss.hasPermi('drama:projects:remove')")
    @Log(title = "项目管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(projectsService.deleteProjectsByIds(ids));
    }
}
