package com.wukai.aiscript.common;

/**
 * 用户上下文工具类
 * 基于 ThreadLocal，确保每个线程（请求）的数据隔离
 */
public class UserContext {
    private static final ThreadLocal<Long> userHolder = new ThreadLocal<>();

    public static void setUserId(Long userId) {
        userHolder.set(userId);
    }

    public static Long getUserId() {
        return userHolder.get();
    }

    public static void clear() {
        userHolder.remove();
    }
}