package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 物理文件对象 storage_files
 * 
 * @author ruoyi
 * @date 2026-02-03
 */
public class StorageFiles extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 空间ID */
    @Excel(name = "空间ID")
    private Long workspaceId;

    /** 上传者ID */
    @Excel(name = "上传者ID")
    private Long uploaderId;

    /** OSS Key */
    @Excel(name = "OSS Key")
    private String fileKey;

    /** 文件名 */
    @Excel(name = "文件名")
    private String originalName;

    /** 类型 */
    @Excel(name = "类型")
    private String mimeType;

    /** 大小 */
    @Excel(name = "大小")
    private Long sizeBytes;

    /** 供应商 */
    @Excel(name = "供应商")
    private String provider;

    /** CDN链接 */
    @Excel(name = "CDN链接")
    private String cdnUrl;

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

    public void setUploaderId(Long uploaderId) 
    {
        this.uploaderId = uploaderId;
    }

    public Long getUploaderId() 
    {
        return uploaderId;
    }

    public void setFileKey(String fileKey) 
    {
        this.fileKey = fileKey;
    }

    public String getFileKey() 
    {
        return fileKey;
    }

    public void setOriginalName(String originalName) 
    {
        this.originalName = originalName;
    }

    public String getOriginalName() 
    {
        return originalName;
    }

    public void setMimeType(String mimeType) 
    {
        this.mimeType = mimeType;
    }

    public String getMimeType() 
    {
        return mimeType;
    }

    public void setSizeBytes(Long sizeBytes) 
    {
        this.sizeBytes = sizeBytes;
    }

    public Long getSizeBytes() 
    {
        return sizeBytes;
    }

    public void setProvider(String provider) 
    {
        this.provider = provider;
    }

    public String getProvider() 
    {
        return provider;
    }

    public void setCdnUrl(String cdnUrl) 
    {
        this.cdnUrl = cdnUrl;
    }

    public String getCdnUrl() 
    {
        return cdnUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("workspaceId", getWorkspaceId())
            .append("uploaderId", getUploaderId())
            .append("fileKey", getFileKey())
            .append("originalName", getOriginalName())
            .append("mimeType", getMimeType())
            .append("sizeBytes", getSizeBytes())
            .append("provider", getProvider())
            .append("cdnUrl", getCdnUrl())
            .append("createTime", getCreateTime())
            .toString();
    }
}
