package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AssetsMapper;
import com.ruoyi.system.domain.Assets;
import com.ruoyi.system.service.IAssetsService;

/**
 * 创作资产Service业务层处理
 * 
 * @author wukai
 * @date 2026-02-03
 */
@Service
public class AssetsServiceImpl implements IAssetsService 
{
    @Autowired
    private AssetsMapper assetsMapper;

    /**
     * 查询创作资产
     * 
     * @param id 创作资产主键
     * @return 创作资产
     */
    @Override
    public Assets selectAssetsById(Long id)
    {
        return assetsMapper.selectAssetsById(id);
    }

    /**
     * 查询创作资产列表
     * 
     * @param assets 创作资产
     * @return 创作资产
     */
    @Override
    public List<Assets> selectAssetsList(Assets assets)
    {
        return assetsMapper.selectAssetsList(assets);
    }

    /**
     * 新增创作资产
     * 
     * @param assets 创作资产
     * @return 结果
     */
    @Override
    public int insertAssets(Assets assets)
    {
        assets.setCreateTime(DateUtils.getNowDate());
        return assetsMapper.insertAssets(assets);
    }

    /**
     * 修改创作资产
     * 
     * @param assets 创作资产
     * @return 结果
     */
    @Override
    public int updateAssets(Assets assets)
    {
        return assetsMapper.updateAssets(assets);
    }

    /**
     * 批量删除创作资产
     * 
     * @param ids 需要删除的创作资产主键
     * @return 结果
     */
    @Override
    public int deleteAssetsByIds(Long[] ids)
    {
        return assetsMapper.deleteAssetsByIds(ids);
    }

    /**
     * 删除创作资产信息
     * 
     * @param id 创作资产主键
     * @return 结果
     */
    @Override
    public int deleteAssetsById(Long id)
    {
        return assetsMapper.deleteAssetsById(id);
    }
}
