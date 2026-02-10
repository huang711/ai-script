package com.ruoyi.drama.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.drama.mapper.ProjectsMapper;
import com.ruoyi.drama.domain.Projects;
import com.ruoyi.drama.service.IProjectsService;
import com.ruoyi.drama.service.IScriptsService;
import com.ruoyi.drama.domain.Scripts;
import com.ruoyi.common.core.context.WorkspaceContextHolder;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 * 项目管理Service业务层处理
 * 
 * @author wukai
 * @date 2026-02-10
 */
@Service
public class ProjectsServiceImpl implements IProjectsService 
{
    @Autowired
    private ProjectsMapper projectsMapper;

    @Autowired
    private IScriptsService scriptsService;

    /**
     * 查询项目管理
     * 
     * @param id 项目管理主键
     * @return 项目管理
     */
    @Override
    public Projects selectProjectsById(Long id)
    {
        return projectsMapper.selectProjectsById(id);
    }

    /**
     * 查询项目管理列表
     * 
     * @param projects 项目管理
     * @return 项目管理
     */
    @Override
    public List<Projects> selectProjectsList(Projects projects)
    {
        projects.setWorkspaceId(WorkspaceContextHolder.getWorkspaceId());
        return projectsMapper.selectProjectsList(projects);
    }

    /**
     * 新增项目管理
     * 
     * @param projects 项目管理
     * @return 结果
     */
    @Override
    @Transactional
    public int insertProjects(Projects projects)
    {
        projects.setWorkspaceId(WorkspaceContextHolder.getWorkspaceId());
        projects.setCreatorId(SecurityUtils.getUserId());
        projects.setCreateTime(DateUtils.getNowDate());
        int rows = projectsMapper.insertProjects(projects);
        
        // 级联创建初始剧本
        Scripts script = new Scripts();
        script.setProjectId(projects.getId());
        script.setTitle("初稿");
        script.setVersion("v1.0");
        script.setCreateTime(DateUtils.getNowDate());
        scriptsService.insertScripts(script);
        
        return rows;
    }

    /**
     * 修改项目管理
     * 
     * @param projects 项目管理
     * @return 结果
     */
    @Override
    public int updateProjects(Projects projects)
    {
        projects.setUpdateTime(DateUtils.getNowDate());
        return projectsMapper.updateProjects(projects);
    }

    /**
     * 批量删除项目管理
     * 
     * @param ids 需要删除的项目管理主键
     * @return 结果
     */
    @Override
    public int deleteProjectsByIds(Long[] ids)
    {
        return projectsMapper.deleteProjectsByIds(ids);
    }

    /**
     * 删除项目管理信息
     * 
     * @param id 项目管理主键
     * @return 结果
     */
    @Override
    public int deleteProjectsById(Long id)
    {
        return projectsMapper.deleteProjectsById(id);
    }
}
