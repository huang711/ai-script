package com.wukai.aiscript.service;

import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wukai.aiscript.dto.LoginRequest;
import com.wukai.aiscript.dto.RegisterRequest;
import com.wukai.aiscript.entity.User;
import com.wukai.aiscript.mapper.UserMapper;
import com.wukai.aiscript.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AuthService {

    @Autowired
    private UserMapper userMapper;

    public String register(RegisterRequest req) {
        // 1. 检查邮箱唯一性
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("email", req.getEmail());
        if (userMapper.selectCount(query) > 0) {
            throw new RuntimeException("邮箱已被注册");
        }

        // 2. 初始化用户
        User user = new User();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        // 加密密码
        user.setPasswordHash(BCrypt.hashpw(req.getPassword()));

        // 初始积分逻辑
        user.setBalanceCredits(BigDecimal.ZERO);
        user.setTotalCreditsPurchased(BigDecimal.ZERO);
        user.setTotalSpentCny(BigDecimal.ZERO);

        user.setStatus(1);
        user.setCreateTime(LocalDateTime.now());

        userMapper.insert(user);
        return "注册成功";
    }

    public String login(LoginRequest req) {
        // 1. 查用户（按用户名或邮箱）
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("username", req.getUsername());
        User user = userMapper.selectOne(query);

        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        // 2. 校验密码
        if (!BCrypt.checkpw(req.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("密码错误");
        }

        // 3. 签发 Token
        return JwtUtils.generateToken(user.getId(), user.getUsername());
    }
}