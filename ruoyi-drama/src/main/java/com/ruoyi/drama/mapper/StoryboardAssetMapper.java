package com.ruoyi.drama.mapper;

import java.util.List;
import com.ruoyi.drama.domain.StoryboardAsset;

/**
 * 分镜资产引用Mapper接口
 * 
 * @author ruoyi
 * @date 2026-02-10
 */
public interface StoryboardAssetMapper 
{
    /**
     * 查询分镜资产引用
     * 
     * @param id 分镜资产引用主键
     * @return 分镜资产引用
     */
    public StoryboardAsset selectStoryboardAssetById(Long id);

    /**
     * 查询分镜资产引用列表
     * 
     * @param storyboardAsset 分镜资产引用
     * @return 分镜资产引用集合
     */
    public List<StoryboardAsset> selectStoryboardAssetList(StoryboardAsset storyboardAsset);

    /**
     * 新增分镜资产引用
     * 
     * @param storyboardAsset 分镜资产引用
     * @return 结果
     */
    public int insertStoryboardAsset(StoryboardAsset storyboardAsset);

    /**
     * 修改分镜资产引用
     * 
     * @param storyboardAsset 分镜资产引用
     * @return 结果
     */
    public int updateStoryboardAsset(StoryboardAsset storyboardAsset);

    /**
     * 删除分镜资产引用
     * 
     * @param id 分镜资产引用主键
     * @return 结果
     */
    public int deleteStoryboardAssetById(Long id);

    /**
     * 批量删除分镜资产引用
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStoryboardAssetByIds(Long[] ids);
}
