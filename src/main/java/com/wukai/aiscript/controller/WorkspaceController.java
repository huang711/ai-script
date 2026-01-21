package com.wukai.aiscript.controller;

import com.wukai.aiscript.common.R; // 之前写的通用返回类
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

    // 创建空间接口
    @PostMapping("/create")
    public R<Workspace> create(@RequestBody Map<String, Object> params) {
        // 临时从参数获取 userId (正常应该从 Token 获取)
        // 前端传参: { "userId": "12345...", "name": "我的AI工作室" }
        String userIdStr = (String) params.get("userId");
        Long userId = Long.parseLong(userIdStr);
        String name = (String) params.get("name");

        Workspace workspace = workspaceService.createWorkspace(userId, name);
        return R.ok(workspace);
    }
}