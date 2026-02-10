package com.ruoyi.drama.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目管理对象 projects
 * 
 * @author wukai
 * @date 2026-02-10
 */
public class Projects extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 空间ID */
    private Long workspaceId;

    /** 类型(personal/team) */
    @Excel(name = "类型")
    private String type;

    /** 创作者ID */
    private Long creatorId;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String name;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

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

    public void setCreatorId(Long creatorId) 
    {
        this.creatorId = creatorId;
    }

    public Long getCreatorId() 
    {
        return creatorId;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("workspaceId", getWorkspaceId())
            .append("type", getType())
            .append("creatorId", getCreatorId())
            .append("name", getName())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
