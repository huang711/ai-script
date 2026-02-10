package com.ruoyi.drama.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目资产关联对象 project_assets
 * 
 * @author wukai
 * @date 2026-02-10
 */
public class ProjectAsset extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 项目ID */
    private Long projectId;

    /** 资产ID */
    @Excel(name = "资产ID")
    private Long assetId;

    /** 使用备注 */
    @Excel(name = "使用备注")
    private String usageNotes;

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

    public void setAssetId(Long assetId) 
    {
        this.assetId = assetId;
    }

    public Long getAssetId() 
    {
        return assetId;
    }

    public void setUsageNotes(String usageNotes) 
    {
        this.usageNotes = usageNotes;
    }

    public String getUsageNotes() 
    {
        return usageNotes;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("projectId", getProjectId())
            .append("assetId", getAssetId())
            .append("usageNotes", getUsageNotes())
            .toString();
    }
}
