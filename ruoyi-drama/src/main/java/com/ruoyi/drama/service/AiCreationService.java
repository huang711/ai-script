package com.ruoyi.drama.service;

/**
 * AI 创作服务接口
 * 
 * @author ruoyi
 */
public interface AiCreationService 
{
    /**
     * 解析剧本
     * 
     * @param scriptId 剧本ID
     */
    public void parseScript(Long scriptId);

    /**
     * 生成分镜
     * 
     * @param episodeId 剧集ID
     */
    public void generateStoryboards(Long episodeId);
}
