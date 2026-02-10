package com.ruoyi.drama.mapper;

import java.util.List;
import com.ruoyi.drama.domain.Scripts;

/**
 * 剧本管理Mapper接口
 * 
 * @author wukai
 * @date 2026-02-10
 */
public interface ScriptsMapper 
{
    /**
     * 查询剧本管理
     * 
     * @param id 剧本管理主键
     * @return 剧本管理
     */
    public Scripts selectScriptsById(Long id);

    /**
     * 查询剧本管理列表
     * 
     * @param scripts 剧本管理
     * @return 剧本管理集合
     */
    public List<Scripts> selectScriptsList(Scripts scripts);

    /**
     * 新增剧本管理
     * 
     * @param scripts 剧本管理
     * @return 结果
     */
    public int insertScripts(Scripts scripts);

    /**
     * 修改剧本管理
     * 
     * @param scripts 剧本管理
     * @return 结果
     */
    public int updateScripts(Scripts scripts);

    /**
     * 删除剧本管理
     * 
     * @param id 剧本管理主键
     * @return 结果
     */
    public int deleteScriptsById(Long id);

    /**
     * 批量删除剧本管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteScriptsByIds(Long[] ids);
}
