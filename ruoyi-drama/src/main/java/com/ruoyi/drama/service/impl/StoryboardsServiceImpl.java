package com.ruoyi.drama.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.drama.mapper.StoryboardsMapper;
import com.ruoyi.drama.mapper.StoryboardAssetMapper;
import com.ruoyi.drama.domain.Storyboards;
import com.ruoyi.drama.domain.StoryboardAsset;
import com.ruoyi.drama.service.IStoryboardsService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 分镜创作Service业务层处理
 * 
 * @author wukai
 * @date 2026-02-10
 */
@Service
public class StoryboardsServiceImpl implements IStoryboardsService 
{
    @Autowired
    private StoryboardsMapper storyboardsMapper;

    @Autowired
    private StoryboardAssetMapper storyboardAssetMapper;

    /**
     * 查询分镜创作
     * 
     * @param id 分镜创作主键
     * @return 分镜创作
     */
    @Override
    public Storyboards selectStoryboardsById(Long id)
    {
        return storyboardsMapper.selectStoryboardsById(id);
    }

    /**
     * 查询分镜创作列表
     * 
     * @param storyboards 分镜创作
     * @return 分镜创作
     */
    @Override
    public List<Storyboards> selectStoryboardsList(Storyboards storyboards)
    {
        return storyboardsMapper.selectStoryboardsList(storyboards);
    }

    /**
     * 新增分镜创作
     * 
     * @param storyboards 分镜创作
     * @return 结果
     */
    @Override
    public int insertStoryboards(Storyboards storyboards)
    {
        storyboards.setCreateTime(DateUtils.getNowDate());
        return storyboardsMapper.insertStoryboards(storyboards);
    }

    /**
     * 修改分镜创作
     * 
     * @param storyboards 分镜创作
     * @return 结果
     */
    @Override
    @Transactional
    public int updateStoryboards(Storyboards storyboards)
    {
        int rows = storyboardsMapper.updateStoryboards(storyboards);
        
        // 更新关联素材
        List<Long> refAssetIds = storyboards.getRefAssetIds();
        if (refAssetIds != null)
        {
            // 1. 删除旧关联
            StoryboardAsset query = new StoryboardAsset();
            query.setStoryboardId(storyboards.getId());
            List<StoryboardAsset> oldAssets = storyboardAssetMapper.selectStoryboardAssetList(query);
            if (oldAssets != null && !oldAssets.isEmpty())
            {
                Long[] ids = oldAssets.stream().map(StoryboardAsset::getId).toArray(Long[]::new);
                storyboardAssetMapper.deleteStoryboardAssetByIds(ids);
            }
            
            // 2. 插入新关联
            for (Long assetId : refAssetIds)
            {
                StoryboardAsset asset = new StoryboardAsset();
                asset.setStoryboardId(storyboards.getId());
                asset.setAssetId(assetId);
                asset.setUsageType("ref");
                asset.setWeight(1L);
                storyboardAssetMapper.insertStoryboardAsset(asset);
            }
        }
        
        return rows;
    }

    /**
     * 批量删除分镜创作
     * 
     * @param ids 需要删除的分镜创作主键
     * @return 结果
     */
    @Override
    public int deleteStoryboardsByIds(Long[] ids)
    {
        return storyboardsMapper.deleteStoryboardsByIds(ids);
    }

    /**
     * 删除分镜创作信息
     * 
     * @param id 分镜创作主键
     * @return 结果
     */
    @Override
    public int deleteStoryboardsById(Long id)
    {
        return storyboardsMapper.deleteStoryboardsById(id);
    }
}
