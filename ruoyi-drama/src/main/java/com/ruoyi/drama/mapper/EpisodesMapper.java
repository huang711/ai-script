package com.ruoyi.drama.mapper;

import java.util.List;
import com.ruoyi.drama.domain.Episodes;

/**
 * 分集管理Mapper接口
 * 
 * @author wukai
 * @date 2026-02-10
 */
public interface EpisodesMapper 
{
    /**
     * 查询分集管理
     * 
     * @param id 分集管理主键
     * @return 分集管理
     */
    public Episodes selectEpisodesById(Long id);

    /**
     * 查询分集管理列表
     * 
     * @param episodes 分集管理
     * @return 分集管理集合
     */
    public List<Episodes> selectEpisodesList(Episodes episodes);

    /**
     * 新增分集管理
     * 
     * @param episodes 分集管理
     * @return 结果
     */
    public int insertEpisodes(Episodes episodes);

    /**
     * 修改分集管理
     * 
     * @param episodes 分集管理
     * @return 结果
     */
    public int updateEpisodes(Episodes episodes);

    /**
     * 删除分集管理
     * 
     * @param id 分集管理主键
     * @return 结果
     */
    public int deleteEpisodesById(Long id);

    /**
     * 批量删除分集管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEpisodesByIds(Long[] ids);
}
