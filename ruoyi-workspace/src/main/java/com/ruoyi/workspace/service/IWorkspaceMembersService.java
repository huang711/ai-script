package com.ruoyi.workspace.service;

import java.util.List;
import com.ruoyi.workspace.domain.WorkspaceMembers;

/**
 * 空间成员Service接口
 * 
 * @author wukai
 * @date 2026-02-10
 */
public interface IWorkspaceMembersService 
{
    /**
     * 查询空间成员
     * 
     * @param id 空间成员主键
     * @return 空间成员
     */
    public WorkspaceMembers selectWorkspaceMembersById(Long id);

    /**
     * 查询空间成员列表
     * 
     * @param workspaceMembers 空间成员
     * @return 空间成员集合
     */
    public List<WorkspaceMembers> selectWorkspaceMembersList(WorkspaceMembers workspaceMembers);

    /**
     * 新增空间成员
     * 
     * @param workspaceMembers 空间成员
     * @return 结果
     */
    public int insertWorkspaceMembers(WorkspaceMembers workspaceMembers);

    /**
     * 修改空间成员
     * 
     * @param workspaceMembers 空间成员
     * @return 结果
     */
    public int updateWorkspaceMembers(WorkspaceMembers workspaceMembers);

    /**
     * 批量删除空间成员
     * 
     * @param ids 需要删除的空间成员主键集合
     * @return 结果
     */
    public int deleteWorkspaceMembersByIds(Long[] ids);

    /**
     * 删除空间成员信息
     * 
     * @param id 空间成员主键
     * @return 结果
     */
    public int deleteWorkspaceMembersById(Long id);
}
