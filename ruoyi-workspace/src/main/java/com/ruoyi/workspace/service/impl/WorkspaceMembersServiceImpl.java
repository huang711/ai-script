package com.ruoyi.workspace.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.workspace.mapper.WorkspaceMembersMapper;
import com.ruoyi.workspace.domain.WorkspaceMembers;
import com.ruoyi.workspace.service.IWorkspaceMembersService;

/**
 * 空间成员Service业务层处理
 * 
 * @author wukai
 * @date 2026-02-10
 */
@Service
public class WorkspaceMembersServiceImpl implements IWorkspaceMembersService 
{
    @Autowired
    private WorkspaceMembersMapper workspaceMembersMapper;

    /**
     * 查询空间成员
     * 
     * @param id 空间成员主键
     * @return 空间成员
     */
    @Override
    public WorkspaceMembers selectWorkspaceMembersById(Long id)
    {
        return workspaceMembersMapper.selectWorkspaceMembersById(id);
    }

    /**
     * 查询空间成员列表
     * 
     * @param workspaceMembers 空间成员
     * @return 空间成员
     */
    @Override
    public List<WorkspaceMembers> selectWorkspaceMembersList(WorkspaceMembers workspaceMembers)
    {
        return workspaceMembersMapper.selectWorkspaceMembersList(workspaceMembers);
    }

    /**
     * 新增空间成员
     * 
     * @param workspaceMembers 空间成员
     * @return 结果
     */
    @Override
    public int insertWorkspaceMembers(WorkspaceMembers workspaceMembers)
    {
        return workspaceMembersMapper.insertWorkspaceMembers(workspaceMembers);
    }

    /**
     * 修改空间成员
     * 
     * @param workspaceMembers 空间成员
     * @return 结果
     */
    @Override
    public int updateWorkspaceMembers(WorkspaceMembers workspaceMembers)
    {
        return workspaceMembersMapper.updateWorkspaceMembers(workspaceMembers);
    }

    /**
     * 批量删除空间成员
     * 
     * @param ids 需要删除的空间成员主键
     * @return 结果
     */
    @Override
    public int deleteWorkspaceMembersByIds(Long[] ids)
    {
        return workspaceMembersMapper.deleteWorkspaceMembersByIds(ids);
    }

    /**
     * 删除空间成员信息
     * 
     * @param id 空间成员主键
     * @return 结果
     */
    @Override
    public int deleteWorkspaceMembersById(Long id)
    {
        return workspaceMembersMapper.deleteWorkspaceMembersById(id);
    }
}
