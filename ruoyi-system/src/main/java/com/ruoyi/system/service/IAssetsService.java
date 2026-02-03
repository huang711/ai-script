package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Assets;

/**
 * 创作资产Service接口
 * 
 * @author wukai
 * @date 2026-02-03
 */
public interface IAssetsService 
{
    /**
     * 查询创作资产
     * 
     * @param id 创作资产主键
     * @return 创作资产
     */
    public Assets selectAssetsById(Long id);

    /**
     * 查询创作资产列表
     * 
     * @param assets 创作资产
     * @return 创作资产集合
     */
    public List<Assets> selectAssetsList(Assets assets);

    /**
     * 新增创作资产
     * 
     * @param assets 创作资产
     * @return 结果
     */
    public int insertAssets(Assets assets);

    /**
     * 修改创作资产
     * 
     * @param assets 创作资产
     * @return 结果
     */
    public int updateAssets(Assets assets);

    /**
     * 批量删除创作资产
     * 
     * @param ids 需要删除的创作资产主键集合
     * @return 结果
     */
    public int deleteAssetsByIds(Long[] ids);

    /**
     * 删除创作资产信息
     * 
     * @param id 创作资产主键
     * @return 结果
     */
    public int deleteAssetsById(Long id);
}
