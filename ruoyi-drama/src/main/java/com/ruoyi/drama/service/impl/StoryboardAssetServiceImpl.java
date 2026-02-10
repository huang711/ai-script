package com.ruoyi.drama.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.drama.mapper.StoryboardAssetMapper;
import com.ruoyi.drama.domain.StoryboardAsset;
import com.ruoyi.drama.service.IStoryboardAssetService;

/**
 * 分镜资产引用Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-02-10
 */
@Service
public class StoryboardAssetServiceImpl implements IStoryboardAssetService 
{
    @Autowired
    private StoryboardAssetMapper storyboardAssetMapper;

    /**
     * 查询分镜资产引用
     * 
     * @param id 分镜资产引用主键
     * @return 分镜资产引用
     */
    @Override
    public StoryboardAsset selectStoryboardAssetById(Long id)
    {
        return storyboardAssetMapper.selectStoryboardAssetById(id);
    }

    /**
     * 查询分镜资产引用列表
     * 
     * @param storyboardAsset 分镜资产引用
     * @return 分镜资产引用
     */
    @Override
    public List<StoryboardAsset> selectStoryboardAssetList(StoryboardAsset storyboardAsset)
    {
        return storyboardAssetMapper.selectStoryboardAssetList(storyboardAsset);
    }

    /**
     * 新增分镜资产引用
     * 
     * @param storyboardAsset 分镜资产引用
     * @return 结果
     */
    @Override
    public int insertStoryboardAsset(StoryboardAsset storyboardAsset)
    {
        return storyboardAssetMapper.insertStoryboardAsset(storyboardAsset);
    }

    /**
     * 修改分镜资产引用
     * 
     * @param storyboardAsset 分镜资产引用
     * @return 结果
     */
    @Override
    public int updateStoryboardAsset(StoryboardAsset storyboardAsset)
    {
        return storyboardAssetMapper.updateStoryboardAsset(storyboardAsset);
    }

    /**
     * 批量删除分镜资产引用
     * 
     * @param ids 需要删除的分镜资产引用主键
     * @return 结果
     */
    @Override
    public int deleteStoryboardAssetByIds(Long[] ids)
    {
        return storyboardAssetMapper.deleteStoryboardAssetByIds(ids);
    }

    /**
     * 删除分镜资产引用信息
     * 
     * @param id 分镜资产引用主键
     * @return 结果
     */
    @Override
    public int deleteStoryboardAssetById(Long id)
    {
        return storyboardAssetMapper.deleteStoryboardAssetById(id);
    }
}
