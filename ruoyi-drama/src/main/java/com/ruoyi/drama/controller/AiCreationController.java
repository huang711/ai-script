package com.ruoyi.drama.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.drama.service.AiCreationService;

/**
 * AI 创作 Controller
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/drama/ai")
public class AiCreationController extends BaseController
{
    @Autowired
    private AiCreationService aiCreationService;

    /**
     * 解析剧本生成分集
     */
    @PreAuthorize("@ss.hasPermi('drama:scripts:edit')")
    @PostMapping("/parse/{scriptId}")
    public AjaxResult parseScript(@PathVariable("scriptId") Long scriptId)
    {
        aiCreationService.parseScript(scriptId);
        return success();
    }

    /**
     * 根据分集生成分镜
     */
    @PreAuthorize("@ss.hasPermi('drama:storyboards:edit')")
    @PostMapping("/generate/{episodeId}")
    public AjaxResult generateStoryboards(@PathVariable("episodeId") Long episodeId)
    {
        aiCreationService.generateStoryboards(episodeId);
        return success();
    }
}
