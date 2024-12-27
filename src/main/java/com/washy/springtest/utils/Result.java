package com.washy.springtest.utils;

import com.washy.springtest.enums.ResponseCodeEnums;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: washy
 * @Date: 2024/06/27/15:16
 */
@Data
@AllArgsConstructor
public class Result<T> {
    /**
     * 空集合
     */
    private static final Object EMPTY_DATA = Collections.emptyMap();
    private static final Result<Object> SUCCESS = new Result<>(ResponseCodeEnums.SUCCESS, EMPTY_DATA);
    private static final Result<Object> FAILURE = new Result<>(ResponseCodeEnums.FAILURE, EMPTY_DATA);

    private Integer code;
    private String msg;
    private T data;

    public Result(ResponseCodeEnums codeEnums, T data) {
        this(codeEnums.getCode(), codeEnums.getMessage(), data);
    }

    /**
     * 自定义响应
     * @param rc 状态码
     * @param mc 消息
     * @param result 响应值
     * @return Result对象
     */
    public static <T> Result<T> custom(Integer rc, String mc, T result) {
        return new Result<>(rc, mc, result);
    }

    /**
     * 指定成功响应值
     * @param result 响应体
     * @param <T> 响应类型
     * @return Result
     */
    public static<T> Result<T> successOf(T result) {
        return new Result<>(ResponseCodeEnums.SUCCESS, result);
    }

    /**
     * 指定失败消息
     * @param msg 失败消息
     * @return Result
     */
    public static Result<Object> failure(String msg) { return new Result<Object>(ResponseCodeEnums.FAILURE.getCode(), msg, null); }

    /**
     * 自动响应(无返回值)
     * @param isSuccess 是否成功
     * @return Result对象
     */
    public static Result<?> auto(boolean isSuccess) {
        return isSuccess ? SUCCESS : FAILURE;
    }

}
