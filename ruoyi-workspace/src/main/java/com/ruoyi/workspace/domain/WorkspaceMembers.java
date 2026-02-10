package com.ruoyi.workspace.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 空间成员对象 workspace_members
 * 
 * @author wukai
 * @date 2026-02-10
 */
public class WorkspaceMembers extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 空间ID */
    private Long workspaceId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 角色 */
    @Excel(name = "角色")
    private Long role;

    /** 加入时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "加入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date joinTime;

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

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setRole(Long role) 
    {
        this.role = role;
    }

    public Long getRole() 
    {
        return role;
    }

    public void setJoinTime(Date joinTime) 
    {
        this.joinTime = joinTime;
    }

    public Date getJoinTime() 
    {
        return joinTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("workspaceId", getWorkspaceId())
            .append("userId", getUserId())
            .append("role", getRole())
            .append("joinTime", getJoinTime())
            .toString();
    }
}
