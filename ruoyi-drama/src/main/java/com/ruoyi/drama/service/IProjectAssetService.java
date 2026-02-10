package com.ruoyi.drama.service;

import java.util.List;
import com.ruoyi.drama.domain.ProjectAsset;

/**
 * 项目资产关联Service接口
 * 
 * @author wukai
 * @date 2026-02-10
 */
public interface IProjectAssetService 
{
    /**
     * 查询项目资产关联
     * 
     * @param id 项目资产关联主键
     * @return 项目资产关联
     */
    public ProjectAsset selectProjectAssetById(Long id);

    /**
     * 查询项目资产关联列表
     * 
     * @param projectAsset 项目资产关联
     * @return 项目资产关联集合
     */
    public List<ProjectAsset> selectProjectAssetList(ProjectAsset projectAsset);

    /**
     * 新增项目资产关联
     * 
     * @param projectAsset 项目资产关联
     * @return 结果
     */
    public int insertProjectAsset(ProjectAsset projectAsset);

    /**
     * 修改项目资产关联
     * 
     * @param projectAsset 项目资产关联
     * @return 结果
     */
    public int updateProjectAsset(ProjectAsset projectAsset);

    /**
     * 批量删除项目资产关联
     * 
     * @param ids 需要删除的项目资产关联主键集合
     * @return 结果
     */
    public int deleteProjectAssetByIds(Long[] ids);

    /**
     * 删除项目资产关联信息
     * 
     * @param id 项目资产关联主键
     * @return 结果
     */
    public int deleteProjectAssetById(Long id);
}
