package com.wukai.aiscript.common;

import lombok.Data;
import java.io.Serializable;

/**
 * 通用返回结果类
 * 所有接口都通过这个格式返回数据给前端
 */
@Data
public class R<T> implements Serializable {

    private Integer code; // 200成功, 500失败
    private String msg;   // 提示信息
    private T data;       // 返回的数据对象

    // 成功的静态方法
    public static <T> R<T> ok(T data) {
        R<T> r = new R<>();
        r.code = 200;
        r.msg = "success";
        r.data = data;
        return r;
    }

    // 失败的静态方法
    public static <T> R<T> error(String msg) {
        R<T> r = new R<>();
        r.code = 500;
        r.msg = msg;
        return r;
    }
}