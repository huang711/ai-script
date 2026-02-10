package com.ruoyi.drama.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 剧本管理对象 scripts
 * 
 * @author wukai
 * @date 2026-02-10
 */
public class Scripts extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 项目ID */
    private Long projectId;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 版本号 */
    @Excel(name = "版本号")
    private String version;

    /** 剧本内容 */
    private String content;

    /** 结构化数据 */
    @Excel(name = "结构化数据")
    private String parsedStructure;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
    }

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setVersion(String version) 
    {
        this.version = version;
    }

    public String getVersion() 
    {
        return version;
    }

    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    public void setParsedStructure(String parsedStructure) 
    {
        this.parsedStructure = parsedStructure;
    }

    public String getParsedStructure() 
    {
        return parsedStructure;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("projectId", getProjectId())
            .append("title", getTitle())
            .append("version", getVersion())
            .append("content", getContent())
            .append("parsedStructure", getParsedStructure())
            .toString();
    }
}
