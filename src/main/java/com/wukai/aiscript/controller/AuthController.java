package com.wukai.aiscript.controller;

import com.wukai.aiscript.common.Result;
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
    public Result<String> register(@RequestBody RegisterRequest req) {
        try {
            String result = authService.register(req);
            return Result.success(result);
        } catch (Exception e) {
            return Result.failed(e.getMessage());
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