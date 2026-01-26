package com.wukai.aiscript.controller;

import com.wukai.aiscript.common.Result;
import com.wukai.aiscript.common.UserContext; // 引入 Context
import com.wukai.aiscript.entity.Workspace;
import com.wukai.aiscript.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/workspace")
public class WorkspaceController {

    @Autowired
    private WorkspaceService workspaceService;

    @PostMapping("/create")
    public Result<Workspace> create(@RequestBody Map<String, Object> params) {
        // 【修改前】 String userIdStr = (String) params.get("userId"); (不安全)

        // 【修改后】 直接从 Token 解析出的上下文中获取当前登录用户 ID
        Long userId = UserContext.getUserId();

        String name = (String) params.get("name");

        // 简单校验
        if (name == null || name.isEmpty()) {
            return Result.failed("空间名称不能为空");
        }

        Workspace workspace = workspaceService.createWorkspace(userId, name);
        return Result.success(workspace);
    }
}