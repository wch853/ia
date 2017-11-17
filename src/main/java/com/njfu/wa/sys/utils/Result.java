package com.njfu.wa.sys.utils;

import com.njfu.wa.sys.enums.ResultEnum;

/**
 * 统一返回结果
 */
public class Result<T> {

    /**
     * 消息代码
     */
    private Integer code;

    /**
     * 消息描述信息
     */
    private String message;

    /**
     * 消息具体内容
     */
    private T data;

    public Result() {
    }

    public Result(String message) {
        this.message = message;
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static Result success(String message) {
        return new Result<>(ResultEnum.SUCCESS.code(), message);
    }

    public static Result fail(String message) {
        return new Result<>(ResultEnum.FAIL.code(), message);
    }

    public static <T> Result<T> data(T data) {
        return new Result<>(ResultEnum.DATA.code(), CommonConstants.DATA_FLAG, data);
    }

    public static <T> Result<T> warn(T warn) {
        return new Result<>(ResultEnum.WARN.code(), CommonConstants.WARN_FLAG, warn);
    }

    public static <T> Result<T> response(ResultEnum resultEnum, String message, T data) {
        return new Result<>(resultEnum.code(), message, data);
    }

    public static <T> Result<T> response(ResultEnum resultEnum) {
        return response(resultEnum, null, null);
    }

}
