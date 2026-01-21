package com.wukai.aiscript.controller;

import com.wukai.aiscript.common.Result; // 1. 引入新类
import com.wukai.aiscript.dto.LoginRequest;
import com.wukai.aiscript.dto.RegisterRequest;
import com.wukai.aiscript.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public Result<String> register(@RequestBody RegisterRequest req) { // 2. 返回 Result
        try {
            String result = authService.register(req);
            return Result.success(result); // 3. 使用 success
        } catch (Exception e) {
            return Result.failed(e.getMessage()); // 4. 使用 failed
        }
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginRequest req) {
        try {
            String token = authService.login(req);
            return Result.success(token);
        } catch (Exception e) {
            return Result.failed(e.getMessage());
        }
    }
}