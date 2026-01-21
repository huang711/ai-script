package com.wukai.aiscript.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("workspaces")
public class Workspace {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String name; // 空间名称，如 "吴凯楷的AI工作室"
    private Long ownerId; // 创建人ID

    private String type; // 类型：PERSONAL(个人), TEAM(团队)

    // JSON 配置字段，使用 Jackson 自动处理（暂时先当 String 存，后续教你用 Handler）
    private String config;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}