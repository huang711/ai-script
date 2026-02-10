package com.ruoyi.assets.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.assets.mapper.AssetsMapper;
import com.ruoyi.assets.domain.Assets;
import com.ruoyi.assets.service.IAssetsService;

/**
 * 素材资产Service业务层处理
 * 
 * @author wukai
 * @date 2026-02-10
 */
@Service
public class AssetsServiceImpl implements IAssetsService 
{
    @Autowired
    private AssetsMapper assetsMapper;

    /**
     * 查询素材资产
     * 
     * @param id 素材资产主键
     * @return 素材资产
     */
    @Override
    public Assets selectAssetsById(Long id)
    {
        return assetsMapper.selectAssetsById(id);
    }

    /**
     * 查询素材资产列表
     * 
     * @param assets 素材资产
     * @return 素材资产
     */
    @Override
    public List<Assets> selectAssetsList(Assets assets)
    {
        return assetsMapper.selectAssetsList(assets);
    }

    /**
     * 新增素材资产
     * 
     * @param assets 素材资产
     * @return 结果
     */
    @Override
    public int insertAssets(Assets assets)
    {
        assets.setCreateTime(DateUtils.getNowDate());
        return assetsMapper.insertAssets(assets);
    }

    /**
     * 修改素材资产
     * 
     * @param assets 素材资产
     * @return 结果
     */
    @Override
    public int updateAssets(Assets assets)
    {
        return assetsMapper.updateAssets(assets);
    }

    /**
     * 批量删除素材资产
     * 
     * @param ids 需要删除的素材资产主键
     * @return 结果
     */
    @Override
    public int deleteAssetsByIds(Long[] ids)
    {
        return assetsMapper.deleteAssetsByIds(ids);
    }

    /**
     * 删除素材资产信息
     * 
     * @param id 素材资产主键
     * @return 结果
     */
    @Override
    public int deleteAssetsById(Long id)
    {
        return assetsMapper.deleteAssetsById(id);
    }
}
