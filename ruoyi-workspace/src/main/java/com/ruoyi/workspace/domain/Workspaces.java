package com.ruoyi.workspace.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工作空间对象 workspaces
 * 
 * @author wukai
 * @date 2026-02-08
 */
public class Workspaces extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 空间名称 */
    @Excel(name = "空间名称")
    private String name;

    /** 拥有者ID */
    private Long ownerId;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 空间配置 */
    private String config;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    public void setOwnerId(Long ownerId) 
    {
        this.ownerId = ownerId;
    }

    public Long getOwnerId() 
    {
        return ownerId;
    }

    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }

    public void setConfig(String config) 
    {
        this.config = config;
    }

    public String getConfig() 
    {
        return config;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("ownerId", getOwnerId())
            .append("type", getType())
            .append("config", getConfig())
            .append("createTime", getCreateTime())
            .toString();
    }
}
