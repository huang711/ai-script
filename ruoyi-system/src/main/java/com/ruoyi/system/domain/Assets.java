package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 创作资产对象 assets
 * 
 * @author wukai
 * @date 2026-02-03
 */
public class Assets extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 空间ID */
    @Excel(name = "空间ID")
    private Long workspaceId;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 封面 */
    @Excel(name = "封面")
    private Long coverFileId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setWorkspaceId(Long workspaceId) 
    {
        this.workspaceId = workspaceId;
    }

    public Long getWorkspaceId() 
    {
        return workspaceId;
    }

    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setCoverFileId(Long coverFileId) 
    {
        this.coverFileId = coverFileId;
    }

    public Long getCoverFileId() 
    {
        return coverFileId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("workspaceId", getWorkspaceId())
            .append("type", getType())
            .append("name", getName())
            .append("description", getDescription())
            .append("coverFileId", getCoverFileId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
