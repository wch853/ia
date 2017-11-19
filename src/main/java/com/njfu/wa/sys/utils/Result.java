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

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 响应结果
     *
     * @param resultEnum resultEnum 结果代码
     * @param message    message
     * @param data       data
     * @param <T>        T
     * @return response
     */
    public static <T> Result<T> response(ResultEnum resultEnum, String message, T data) {
        return new Result<>(resultEnum.code(), message, data);
    }

    /**
     * 响应结果
     *
     * @param resultEnum resultEnum 结果代码
     * @param data       data
     * @param <T>        T
     * @return response
     */
    public static <T> Result<T> response(ResultEnum resultEnum, T data) {
        return new Result<>(resultEnum.code(), null, data);
    }

    /**
     * 响应结果
     *
     * @param resultEnum resultEnum 结果代码
     * @param <T>        T
     * @return response
     */
    public static <T> Result<T> response(ResultEnum resultEnum) {
        return response(resultEnum, null);
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
}
