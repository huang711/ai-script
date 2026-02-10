package com.ruoyi.drama.service;

import java.util.List;
import com.ruoyi.drama.domain.Storyboards;

/**
 * 分镜创作Service接口
 * 
 * @author wukai
 * @date 2026-02-10
 */
public interface IStoryboardsService 
{
    /**
     * 查询分镜创作
     * 
     * @param id 分镜创作主键
     * @return 分镜创作
     */
    public Storyboards selectStoryboardsById(Long id);

    /**
     * 查询分镜创作列表
     * 
     * @param storyboards 分镜创作
     * @return 分镜创作集合
     */
    public List<Storyboards> selectStoryboardsList(Storyboards storyboards);

    /**
     * 新增分镜创作
     * 
     * @param storyboards 分镜创作
     * @return 结果
     */
    public int insertStoryboards(Storyboards storyboards);

    /**
     * 修改分镜创作
     * 
     * @param storyboards 分镜创作
     * @return 结果
     */
    public int updateStoryboards(Storyboards storyboards);

    /**
     * 批量删除分镜创作
     * 
     * @param ids 需要删除的分镜创作主键集合
     * @return 结果
     */
    public int deleteStoryboardsByIds(Long[] ids);

    /**
     * 删除分镜创作信息
     * 
     * @param id 分镜创作主键
     * @return 结果
     */
    public int deleteStoryboardsById(Long id);
}
