package com.ruoyi.drama.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.drama.mapper.ProjectAssetMapper;
import com.ruoyi.drama.domain.ProjectAsset;
import com.ruoyi.drama.service.IProjectAssetService;

/**
 * 项目资产关联Service业务层处理
 * 
 * @author wukai
 * @date 2026-02-10
 */
@Service
public class ProjectAssetServiceImpl implements IProjectAssetService 
{
    @Autowired
    private ProjectAssetMapper projectAssetMapper;

    /**
     * 查询项目资产关联
     * 
     * @param id 项目资产关联主键
     * @return 项目资产关联
     */
    @Override
    public ProjectAsset selectProjectAssetById(Long id)
    {
        return projectAssetMapper.selectProjectAssetById(id);
    }

    /**
     * 查询项目资产关联列表
     * 
     * @param projectAsset 项目资产关联
     * @return 项目资产关联
     */
    @Override
    public List<ProjectAsset> selectProjectAssetList(ProjectAsset projectAsset)
    {
        return projectAssetMapper.selectProjectAssetList(projectAsset);
    }

    /**
     * 新增项目资产关联
     * 
     * @param projectAsset 项目资产关联
     * @return 结果
     */
    @Override
    public int insertProjectAsset(ProjectAsset projectAsset)
    {
        return projectAssetMapper.insertProjectAsset(projectAsset);
    }

    /**
     * 修改项目资产关联
     * 
     * @param projectAsset 项目资产关联
     * @return 结果
     */
    @Override
    public int updateProjectAsset(ProjectAsset projectAsset)
    {
        return projectAssetMapper.updateProjectAsset(projectAsset);
    }

    /**
     * 批量删除项目资产关联
     * 
     * @param ids 需要删除的项目资产关联主键
     * @return 结果
     */
    @Override
    public int deleteProjectAssetByIds(Long[] ids)
    {
        return projectAssetMapper.deleteProjectAssetByIds(ids);
    }

    /**
     * 删除项目资产关联信息
     * 
     * @param id 项目资产关联主键
     * @return 结果
     */
    @Override
    public int deleteProjectAssetById(Long id)
    {
        return projectAssetMapper.deleteProjectAssetById(id);
    }
}
