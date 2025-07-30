package com.blog.framework.handler;


import com.blog.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        // 记录详细错误日志，方便排查
        log.error("系统出现未知异常: {}", e.getMessage(), e);
        // 向前端返回通用错误信息
        return Result.error(500, "系统开小差了，请稍后重试");
    }
    // 后续可以添加更多针对特定异常的处理，如：
    // @ExceptionHandler(BusinessException.class)
    // public Result handleBusinessException(BusinessException e) { ... }
}