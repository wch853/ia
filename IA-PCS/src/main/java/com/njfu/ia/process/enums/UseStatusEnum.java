package com.njfu.ia.process.enums;

/**
 * 存放使用状态
 */
public enum UseStatusEnum {

    /**
     * 未使用
     */
    UNUSE(0),
    /**
     * 使用中
     */
    INUSE(1),
    /**
     * 故障中
     */
    ERROR(2);

    private int code;

    UseStatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
