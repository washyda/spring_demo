package com.washy.springtest.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: washy
 * @Date: 2024/07/02/9:37
 */
@Getter
@AllArgsConstructor
public enum ResponseCodeEnums {
    /**
     * 响应枚举
     */
    FAILURE(1, "FAILURE"),
    PARAM_NOT_VALID(2, "参数校验失败"),
    SUCCESS(0, "SUCCESS"),
    UNAUTHORIZED(3, "未授权");

    private final Integer code;
    private final String message;
}
