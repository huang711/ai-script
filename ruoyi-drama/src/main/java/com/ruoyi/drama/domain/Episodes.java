package com.ruoyi.drama.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 分集管理对象 episodes
 * 
 * @author wukai
 * @date 2026-02-10
 */
public class Episodes extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 剧本编号 */
    @Excel(name = "剧本编号")
    private Long scriptId;

    /** 集数 */
    @Excel(name = "集数")
    private Long episodeNum;

    /** 分集标题 */
    @Excel(name = "分集标题")
    private String title;

    /** 分集梗概 */
    @Excel(name = "分集梗概")
    private String summary;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setScriptId(Long scriptId) 
    {
        this.scriptId = scriptId;
    }

    public Long getScriptId() 
    {
        return scriptId;
    }

    public void setEpisodeNum(Long episodeNum) 
    {
        this.episodeNum = episodeNum;
    }

    public Long getEpisodeNum() 
    {
        return episodeNum;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setSummary(String summary) 
    {
        this.summary = summary;
    }

    public String getSummary() 
    {
        return summary;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("scriptId", getScriptId())
            .append("episodeNum", getEpisodeNum())
            .append("title", getTitle())
            .append("summary", getSummary())
            .toString();
    }
}
