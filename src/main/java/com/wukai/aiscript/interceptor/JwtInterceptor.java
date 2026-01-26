package com.wukai.aiscript.interceptor;

import com.wukai.aiscript.common.UserContext;
import com.wukai.aiscript.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 获取请求头中的 Token
        String token = request.getHeader("Authorization");

        // 2. 校验 Token 格式 (通常是 "Bearer xxxxx"，这里简单处理，支持直接传 token 或带 Bearer)
        if (!StringUtils.hasLength(token)) {
            response.setStatus(401); // 无权访问
            return false;
        }

        // 兼容 Bearer 开头的标准写法
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        // 3. 验证 Token 有效性
        Long userId = JwtUtils.getUserId(token);
        if (userId == null) {
            response.setStatus(401); // Token 无效或过期
            return false;
        }

        // 4. 将用户 ID 存入上下文，供后续 Controller 使用
        UserContext.setUserId(userId);

        // 5. 放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 请求结束，清理 ThreadLocal，防止内存泄漏
        UserContext.clear();
    }
}