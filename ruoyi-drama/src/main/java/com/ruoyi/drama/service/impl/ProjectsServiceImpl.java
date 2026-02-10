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

import com.ruoyi.workspace.domain.Workspaces;
import com.ruoyi.workspace.service.IWorkspacesService;

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

    @Autowired
    private IWorkspacesService workspacesService;

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
        Long workspaceId = WorkspaceContextHolder.getWorkspaceId();
        projects.setWorkspaceId(workspaceId);
        
        // 获取当前空间类型并设置查询条件
        /*
        if (workspaceId != null)
        {
            Workspaces workspace = workspacesService.selectWorkspacesById(workspaceId);
            if (workspace != null)
            {
                projects.setType(workspace.getType());
            }
        }
        */
        
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
        Long workspaceId = WorkspaceContextHolder.getWorkspaceId();
        projects.setWorkspaceId(workspaceId);
        
        // 设置项目类型
        if (workspaceId != null)
        {
            Workspaces workspace = workspacesService.selectWorkspacesById(workspaceId);
            if (workspace != null)
            {
                projects.setType(workspace.getType());
            }
        }
        
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

    /**
     * 将项目移动到团队空间
     * 
     * @param id 项目ID
     * @return 结果
     */
    @Override
    @Transactional
    public int moveToTeam(Long id)
    {
        // 1. 获取项目信息
        Projects project = projectsMapper.selectProjectsById(id);
        if (project == null)
        {
            throw new RuntimeException("项目不存在");
        }

        // 2. 检查当前空间类型
        Workspaces currentWorkspace = workspacesService.selectWorkspacesById(project.getWorkspaceId());
        if (currentWorkspace == null || !"personal".equals(currentWorkspace.getType()))
        {
            throw new RuntimeException("只有个人空间的项目可以移动到团队空间");
        }

        // 3. 获取用户ID
        Long userId = SecurityUtils.getUserId();

        // 4. 查找用户的团队空间
        Workspaces query = new Workspaces();
        query.setOwnerId(userId);
        query.setType("team");
        List<Workspaces> teamWorkspaces = workspacesService.selectWorkspacesList(query);
        
        if (teamWorkspaces.isEmpty())
        {
            throw new RuntimeException("未找到团队空间");
        }
        
        // 假设每个用户只有一个团队空间
        Workspaces teamWorkspace = teamWorkspaces.get(0);

        // 5. 更新项目的空间ID和类型
        project.setWorkspaceId(teamWorkspace.getId());
        project.setType("team");
        project.setUpdateTime(DateUtils.getNowDate());
        
        return projectsMapper.updateProjects(project);
    }
}
