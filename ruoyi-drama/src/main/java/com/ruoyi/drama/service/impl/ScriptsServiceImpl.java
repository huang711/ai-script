package com.ruoyi.drama.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.drama.mapper.ScriptsMapper;
import com.ruoyi.drama.domain.Scripts;
import com.ruoyi.drama.service.IScriptsService;

/**
 * 剧本管理Service业务层处理
 * 
 * @author wukai
 * @date 2026-02-10
 */
@Service
public class ScriptsServiceImpl implements IScriptsService 
{
    @Autowired
    private ScriptsMapper scriptsMapper;

    /**
     * 查询剧本管理
     * 
     * @param id 剧本管理主键
     * @return 剧本管理
     */
    @Override
    public Scripts selectScriptsById(Long id)
    {
        return scriptsMapper.selectScriptsById(id);
    }

    /**
     * 查询剧本管理列表
     * 
     * @param scripts 剧本管理
     * @return 剧本管理
     */
    @Override
    public List<Scripts> selectScriptsList(Scripts scripts)
    {
        return scriptsMapper.selectScriptsList(scripts);
    }

    /**
     * 新增剧本管理
     * 
     * @param scripts 剧本管理
     * @return 结果
     */
    @Override
    public int insertScripts(Scripts scripts)
    {
        return scriptsMapper.insertScripts(scripts);
    }

    /**
     * 修改剧本管理
     * 
     * @param scripts 剧本管理
     * @return 结果
     */
    @Override
    public int updateScripts(Scripts scripts)
    {
        return scriptsMapper.updateScripts(scripts);
    }

    /**
     * 批量删除剧本管理
     * 
     * @param ids 需要删除的剧本管理主键
     * @return 结果
     */
    @Override
    public int deleteScriptsByIds(Long[] ids)
    {
        return scriptsMapper.deleteScriptsByIds(ids);
    }

    /**
     * 删除剧本管理信息
     * 
     * @param id 剧本管理主键
     * @return 结果
     */
    @Override
    public int deleteScriptsById(Long id)
    {
        return scriptsMapper.deleteScriptsById(id);
    }
}
