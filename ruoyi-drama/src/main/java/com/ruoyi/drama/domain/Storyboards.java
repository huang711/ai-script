package com.ruoyi.drama.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 分镜创作对象 storyboards
 * 
 * @author wukai
 * @date 2026-02-10
 */
public class Storyboards extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 剧集编号 */
    private Long episodeId;

    /** 镜号 */
    @Excel(name = "镜号")
    private Long shotNum;

    /** 画面描述 */
    @Excel(name = "画面描述")
    private String description;

    /** 对白 */
    private String dialogue;

    /** 运镜 */
    @Excel(name = "运镜")
    private String cameraMovement;

    /** 时长 */
    @Excel(name = "时长")
    private String duration;

    /** 图像文件ID */
    @Excel(name = "图像文件ID")
    private Long imageFileId;

    /** 视频文件ID */
    private Long videoFileId;

    /** 音频文件ID */
    private Long audioFileId;

    /** AI提示词 */
    private String prompt;

    /** AI参数 */
    private String aiParams;

    /** 关联素材ID列表 */
    private List<Long> refAssetIds;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setEpisodeId(Long episodeId) 
    {
        this.episodeId = episodeId;
    }

    public Long getEpisodeId() 
    {
        return episodeId;
    }

    public void setShotNum(Long shotNum) 
    {
        this.shotNum = shotNum;
    }

    public Long getShotNum() 
    {
        return shotNum;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public void setDialogue(String dialogue) 
    {
        this.dialogue = dialogue;
    }

    public String getDialogue() 
    {
        return dialogue;
    }

    public void setCameraMovement(String cameraMovement) 
    {
        this.cameraMovement = cameraMovement;
    }

    public String getCameraMovement() 
    {
        return cameraMovement;
    }

    public void setDuration(String duration) 
    {
        this.duration = duration;
    }

    public String getDuration() 
    {
        return duration;
    }

    public void setImageFileId(Long imageFileId) 
    {
        this.imageFileId = imageFileId;
    }

    public Long getImageFileId() 
    {
        return imageFileId;
    }

    public void setVideoFileId(Long videoFileId) 
    {
        this.videoFileId = videoFileId;
    }

    public Long getVideoFileId() 
    {
        return videoFileId;
    }

    public void setAudioFileId(Long audioFileId) 
    {
        this.audioFileId = audioFileId;
    }

    public Long getAudioFileId() 
    {
        return audioFileId;
    }

    public void setPrompt(String prompt) 
    {
        this.prompt = prompt;
    }

    public String getPrompt() 
    {
        return prompt;
    }

    public void setAiParams(String aiParams) 
    {
        this.aiParams = aiParams;
    }

    public String getAiParams() 
    {
        return aiParams;
    }

    public void setRefAssetIds(List<Long> refAssetIds)
    {
        this.refAssetIds = refAssetIds;
    }

    public List<Long> getRefAssetIds()
    {
        return refAssetIds;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("episodeId", getEpisodeId())
            .append("shotNum", getShotNum())
            .append("description", getDescription())
            .append("dialogue", getDialogue())
            .append("cameraMovement", getCameraMovement())
            .append("duration", getDuration())
            .append("imageFileId", getImageFileId())
            .append("videoFileId", getVideoFileId())
            .append("audioFileId", getAudioFileId())
            .append("prompt", getPrompt())
            .append("aiParams", getAiParams())
            .append("createTime", getCreateTime())
            .toString();
    }
}
