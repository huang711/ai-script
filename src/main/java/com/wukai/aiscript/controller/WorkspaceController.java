package com.wukai.aiscript.controller;

import com.wukai.aiscript.common.Result; // 1. 引入新类
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
    public Result<Workspace> create(@RequestBody Map<String, Object> params) { // 2. 返回 Result
        try {
            // 参数解析逻辑不变...
            String userIdStr = (String) params.get("userId");
            // 简单做个空指针保护
            if (userIdStr == null) {
                return Result.failed("userId不能为空");
            }
            Long userId = Long.parseLong(userIdStr);
            String name = (String) params.get("name");

            Workspace workspace = workspaceService.createWorkspace(userId, name);
            return Result.success(workspace); // 3. 使用 success

        } catch (Exception e) {
            return Result.failed("创建失败：" + e.getMessage());
        }
    }
}