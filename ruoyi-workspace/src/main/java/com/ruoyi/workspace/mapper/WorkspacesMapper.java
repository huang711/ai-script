package com.ruoyi.workspace.mapper;

import java.util.List;
import com.ruoyi.workspace.domain.Workspaces;

/**
 * 工作空间Mapper接口
 * 
 * @author wukai
 * @date 2026-02-08
 */
public interface WorkspacesMapper 
{
    /**
     * 查询工作空间
     * 
     * @param id 工作空间主键
     * @return 工作空间
     */
    public Workspaces selectWorkspacesById(Long id);

    /**
     * 查询工作空间列表
     * 
     * @param workspaces 工作空间
     * @return 工作空间集合
     */
    public List<Workspaces> selectWorkspacesList(Workspaces workspaces);

    /**
     * 新增工作空间
     * 
     * @param workspaces 工作空间
     * @return 结果
     */
    public int insertWorkspaces(Workspaces workspaces);

    /**
     * 修改工作空间
     * 
     * @param workspaces 工作空间
     * @return 结果
     */
    public int updateWorkspaces(Workspaces workspaces);

    /**
     * 删除工作空间
     * 
     * @param id 工作空间主键
     * @return 结果
     */
    public int deleteWorkspacesById(Long id);

    /**
     * 批量删除工作空间
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkspacesByIds(Long[] ids);
}
