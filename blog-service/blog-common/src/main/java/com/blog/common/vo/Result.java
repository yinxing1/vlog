package com.blog.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    private Integer code;
    private String message;
    private T data;

    // 静态工厂方法，方便快速创建成功的响应
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data);
    }

    public static Result success() {
        return success(null);
    }

    // 静态工厂方法，方便快速创建失败的响应
    public static Result error(int code, String message) {
        return new Result<>(code, message, null);
    }
}