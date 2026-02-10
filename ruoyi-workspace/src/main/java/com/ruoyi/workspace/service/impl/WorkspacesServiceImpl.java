package com.ruoyi.workspace.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.workspace.mapper.WorkspacesMapper;
import com.ruoyi.workspace.domain.Workspaces;
import com.ruoyi.workspace.domain.WorkspaceMembers;
import com.ruoyi.workspace.service.IWorkspacesService;
import com.ruoyi.workspace.service.IWorkspaceMembersService;

/**
 * 工作空间Service业务层处理
 *
 * @author wukai
 * @date 2026-02-08
 */
@Service
public class WorkspacesServiceImpl implements IWorkspacesService
{
    @Autowired
    private WorkspacesMapper workspacesMapper;

    @Autowired
    private IWorkspaceMembersService workspaceMembersService;

    /**
     * 查询工作空间
     *
     * @param id 工作空间主键
     * @return 工作空间
     */
    @Override
    public Workspaces selectWorkspacesById(Long id)
    {
        return workspacesMapper.selectWorkspacesById(id);
    }

    /**
     * 查询工作空间列表
     *
     * @param workspaces 工作空间
     * @return 工作空间
     */
    @Override
    public List<Workspaces> selectWorkspacesList(Workspaces workspaces)
    {
        return workspacesMapper.selectWorkspacesList(workspaces);
    }

    /**
     * 新增工作空间
     *
     * @param workspaces 工作空间
     * @return 结果
     */
    @Override
    public int insertWorkspaces(Workspaces workspaces)
    {
        workspaces.setCreateTime(DateUtils.getNowDate());
        return workspacesMapper.insertWorkspaces(workspaces);
    }

    /**
     * 修改工作空间
     *
     * @param workspaces 工作空间
     * @return 结果
     */
    @Override
    public int updateWorkspaces(Workspaces workspaces)
    {
        return workspacesMapper.updateWorkspaces(workspaces);
    }

    /**
     * 批量删除工作空间
     *
     * @param ids 需要删除的工作空间主键
     * @return 结果
     */
    @Override
    public int deleteWorkspacesByIds(Long[] ids)
    {
        return workspacesMapper.deleteWorkspacesByIds(ids);
    }

    /**
     * 删除工作空间信息
     *
     * @param id 工作空间主键
     * @return 结果
     */
    @Override
    public int deleteWorkspacesById(Long id)
    {
        return workspacesMapper.deleteWorkspacesById(id);
    }

    /**
     * 初始化个人空间
     * 
     * @param userId 用户ID
     */
    @Override
    public void initPersonalWorkspace(Long userId)
    {
        Workspaces query = new Workspaces();
        query.setOwnerId(userId);
        query.setType("personal");
        List<Workspaces> list = workspacesMapper.selectWorkspacesList(query);
        
        if (list.isEmpty())
        {
            Workspaces ws = new Workspaces();
            ws.setName("个人空间");
            ws.setOwnerId(userId);
            ws.setType("personal");
            ws.setCreateTime(DateUtils.getNowDate());
            workspacesMapper.insertWorkspaces(ws);
            
            WorkspaceMembers member = new WorkspaceMembers();
            member.setWorkspaceId(ws.getId());
            member.setUserId(userId);
            member.setRole(1L); // 1=Admin
            member.setJoinTime(DateUtils.getNowDate());
            workspaceMembersService.insertWorkspaceMembers(member);
        }
    }
}
