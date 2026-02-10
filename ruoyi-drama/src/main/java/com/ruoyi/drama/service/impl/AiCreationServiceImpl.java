package com.ruoyi.drama.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.drama.domain.Episodes;
import com.ruoyi.drama.domain.Scripts;
import com.ruoyi.drama.domain.Storyboards;
import com.ruoyi.drama.mapper.EpisodesMapper;
import com.ruoyi.drama.mapper.ScriptsMapper;
import com.ruoyi.drama.mapper.StoryboardsMapper;
import com.ruoyi.drama.service.AiCreationService;
import com.ruoyi.common.utils.DateUtils;

/**
 * AI 创作服务实现
 * 
 * @author ruoyi
 */
@Service
public class AiCreationServiceImpl implements AiCreationService
{
    @Autowired
    private ScriptsMapper scriptsMapper;

    @Autowired
    private EpisodesMapper episodesMapper;

    @Autowired
    private StoryboardsMapper storyboardsMapper;

    /**
     * 解析剧本
     * 
     * @param scriptId 剧本ID
     */
    @Override
    @Transactional
    public void parseScript(Long scriptId)
    {
        // 1. 查出 Script 内容
        Scripts script = scriptsMapper.selectScriptsById(scriptId);
        if (script == null)
        {
            throw new RuntimeException("剧本不存在");
        }

        // 2. (模拟) 清空该 Script 下旧的 Episodes
        Episodes episodeQuery = new Episodes();
        episodeQuery.setScriptId(scriptId);
        List<Episodes> oldEpisodes = episodesMapper.selectEpisodesList(episodeQuery);
        if (oldEpisodes != null && !oldEpisodes.isEmpty())
        {
            Long[] ids = oldEpisodes.stream().map(Episodes::getId).toArray(Long[]::new);
            episodesMapper.deleteEpisodesByIds(ids);
        }

        // 3. (模拟) 按简单的规则创建 Episodes 数据
        String[] titles = {"第一集：初识", "第二集：冲突", "第三集：高潮"};
        for (int i = 0; i < titles.length; i++)
        {
            Episodes episode = new Episodes();
            episode.setScriptId(scriptId);
            episode.setEpisodeNum((long) (i + 1));
            episode.setTitle(titles[i]);
            episode.setSummary("这是" + titles[i] + "的剧情梗概模拟数据...");
            episode.setCreateTime(DateUtils.getNowDate());
            episodesMapper.insertEpisodes(episode);
        }
    }

    /**
     * 生成分镜
     * 
     * @param episodeId 剧集ID
     */
    @Override
    @Transactional
    public void generateStoryboards(Long episodeId)
    {
        // 1. 查出 Episode 信息
        Episodes episode = episodesMapper.selectEpisodesById(episodeId);
        if (episode == null)
        {
            throw new RuntimeException("剧集不存在");
        }

        // 2. (模拟) 清空该 Episode 下旧的 Storyboards
        Storyboards storyboardQuery = new Storyboards();
        storyboardQuery.setEpisodeId(episodeId);
        List<Storyboards> oldStoryboards = storyboardsMapper.selectStoryboardsList(storyboardQuery);
        if (oldStoryboards != null && !oldStoryboards.isEmpty())
        {
            Long[] ids = oldStoryboards.stream().map(Storyboards::getId).toArray(Long[]::new);
            storyboardsMapper.deleteStoryboardsByIds(ids);
        }

        // 3. (模拟) 循环生成 4-5 个 Storyboards 数据
        int count = 5;
        String[] cameraMovements = {"推", "拉", "摇", "移", "跟"};
        
        for (int i = 1; i <= count; i++)
        {
            Storyboards storyboard = new Storyboards();
            storyboard.setEpisodeId(episodeId);
            storyboard.setShotNum((long) i);
            storyboard.setDescription("模拟画面：主角走进房间，看到桌上有一封信（场景 " + i + "）...");
            storyboard.setDialogue("主角：这是什么？");
            
            // 随机运镜
            int randomIdx = (int) (Math.random() * cameraMovements.length);
            storyboard.setCameraMovement(cameraMovements[randomIdx]);
            
            storyboard.setDuration("3s");
            storyboard.setCreateTime(DateUtils.getNowDate());
            storyboardsMapper.insertStoryboards(storyboard);
        }
    }
}
