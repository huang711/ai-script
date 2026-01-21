package com.wukai.aiscript.controller;

import com.wukai.aiscript.common.R;
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
    public R<String> register(@RequestBody RegisterRequest req) {
        try {
            String result = authService.register(req);
            return R.ok(result);
        } catch (Exception e) {
            return R.error(e.getMessage());
        }
    }

    @PostMapping("/login")
    public R<String> login(@RequestBody LoginRequest req) {
        try {
            String token = authService.login(req);
            return R.ok(token);
        } catch (Exception e) {
            return R.error(e.getMessage());
        }
    }
}