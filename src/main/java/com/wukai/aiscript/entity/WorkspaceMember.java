package com.wukai.aiscript.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("workspace_members")
public class WorkspaceMember {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private Long workspaceId;

    private Long userId;

    /**
     * 角色定义:
     * 1 = Editor (编辑)
     * 9 = Admin (管理员)
     * 10 = Owner (拥有者)
     */
    private Integer role;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime joinTime;
}