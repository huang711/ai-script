package com.wukai.aiscript.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("users")
public class User {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String username;

    private String email;

    // 对应数据库 password_hash
    private String passwordHash;

    private Long avatarFileId;

    // 商业化字段
    private BigDecimal balanceCredits;
    private BigDecimal totalCreditsPurchased;
    private BigDecimal totalSpentCny;

    private Integer status; // 1:正常, 0:禁用

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}