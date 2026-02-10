package com.ruoyi.assets.mapper;

import java.util.List;
import com.ruoyi.assets.domain.Assets;

/**
 * 素材资产Mapper接口
 * 
 * @author wukai
 * @date 2026-02-10
 */
public interface AssetsMapper 
{
    /**
     * 查询素材资产
     * 
     * @param id 素材资产主键
     * @return 素材资产
     */
    public Assets selectAssetsById(Long id);

    /**
     * 查询素材资产列表
     * 
     * @param assets 素材资产
     * @return 素材资产集合
     */
    public List<Assets> selectAssetsList(Assets assets);

    /**
     * 新增素材资产
     * 
     * @param assets 素材资产
     * @return 结果
     */
    public int insertAssets(Assets assets);

    /**
     * 修改素材资产
     * 
     * @param assets 素材资产
     * @return 结果
     */
    public int updateAssets(Assets assets);

    /**
     * 删除素材资产
     * 
     * @param id 素材资产主键
     * @return 结果
     */
    public int deleteAssetsById(Long id);

    /**
     * 批量删除素材资产
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAssetsByIds(Long[] ids);
}
