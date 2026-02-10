package com.ruoyi.drama.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.drama.mapper.EpisodesMapper;
import com.ruoyi.drama.domain.Episodes;
import com.ruoyi.drama.service.IEpisodesService;

/**
 * 分集管理Service业务层处理
 * 
 * @author wukai
 * @date 2026-02-10
 */
@Service
public class EpisodesServiceImpl implements IEpisodesService 
{
    @Autowired
    private EpisodesMapper episodesMapper;

    /**
     * 查询分集管理
     * 
     * @param id 分集管理主键
     * @return 分集管理
     */
    @Override
    public Episodes selectEpisodesById(Long id)
    {
        return episodesMapper.selectEpisodesById(id);
    }

    /**
     * 查询分集管理列表
     * 
     * @param episodes 分集管理
     * @return 分集管理
     */
    @Override
    public List<Episodes> selectEpisodesList(Episodes episodes)
    {
        return episodesMapper.selectEpisodesList(episodes);
    }

    /**
     * 新增分集管理
     * 
     * @param episodes 分集管理
     * @return 结果
     */
    @Override
    public int insertEpisodes(Episodes episodes)
    {
        return episodesMapper.insertEpisodes(episodes);
    }

    /**
     * 修改分集管理
     * 
     * @param episodes 分集管理
     * @return 结果
     */
    @Override
    public int updateEpisodes(Episodes episodes)
    {
        return episodesMapper.updateEpisodes(episodes);
    }

    /**
     * 批量删除分集管理
     * 
     * @param ids 需要删除的分集管理主键
     * @return 结果
     */
    @Override
    public int deleteEpisodesByIds(Long[] ids)
    {
        return episodesMapper.deleteEpisodesByIds(ids);
    }

    /**
     * 删除分集管理信息
     * 
     * @param id 分集管理主键
     * @return 结果
     */
    @Override
    public int deleteEpisodesById(Long id)
    {
        return episodesMapper.deleteEpisodesById(id);
    }
}
