package com.ruoyi.drama.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 分镜资产引用对象 storyboard_assets
 * 
 * @author ruoyi
 * @date 2026-02-10
 */
public class StoryboardAsset extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 分镜ID */
    private Long storyboardId;

    /** 资产ID */
    @Excel(name = "资产ID")
    private Long assetId;

    /** 权重 */
    @Excel(name = "权重")
    private Long weight;

    /** 用途类型 */
    @Excel(name = "用途类型")
    private String usageType;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setStoryboardId(Long storyboardId) 
    {
        this.storyboardId = storyboardId;
    }

    public Long getStoryboardId() 
    {
        return storyboardId;
    }

    public void setAssetId(Long assetId) 
    {
        this.assetId = assetId;
    }

    public Long getAssetId() 
    {
        return assetId;
    }

    public void setWeight(Long weight) 
    {
        this.weight = weight;
    }

    public Long getWeight() 
    {
        return weight;
    }

    public void setUsageType(String usageType) 
    {
        this.usageType = usageType;
    }

    public String getUsageType() 
    {
        return usageType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("storyboardId", getStoryboardId())
            .append("assetId", getAssetId())
            .append("weight", getWeight())
            .append("usageType", getUsageType())
            .toString();
    }
}
