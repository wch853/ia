package com.njfu.wa.sys.enums;

/**
 * 存放结果代码
 */
public enum ResultEnum {

    /**
     * 成功
     */
    SUCCESS(200),
    /**
     * 数据
     */
    DATA(201),
    /**
     * 警告数据
     */
    WARN(202),
    /**
     * 失败
     */
    FAIL(300),
    /**
     * 非法数据请求
     */
    BAD_REQUEST(400),
    /**
     * 无权限
     */
    UNAUTHORIZED(403);

    /**
     * 结果代码
     */
    private Integer code;

    ResultEnum(Integer code) {
        this.code = code;
    }

    public Integer code() {
        return code;
    }
}
