package com.wukai.aiscript.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("workspace_members")
public class WorkspaceMember {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private Long workspaceId; // 关联哪个空间

    private Long userId; // 关联哪个用户

    // 角色：1=Owner(拥有者), 2=Admin(管理员), 3=Editor(编辑), 4=Viewer(只读)
    private Integer role;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime joinTime;
}