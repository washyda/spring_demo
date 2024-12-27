package com.washy.springtest.exception;

import com.washy.springtest.utils.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: washy
 * @Date: 2024/06/27/14:28
 */
@RestControllerAdvice(basePackages = "com.washy.springtest.controller")
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {GlobalException.class})
    public Result<String> handleCustomException(GlobalException ex) {
        // 这里可以自定义错误响应体，例如：
        return Result.custom(ex.getCode(), ex.getMessage(), "");
    }
}
