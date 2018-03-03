package com.njfu.ia.sys.enums;

/**
 * 存放使用状态
 */
public enum UseStatusEnum {

    /**
     * 未使用
     */
    UNUSE("0"),
    /**
     * 使用中
     */
    INUSE("1"),
    /**
     * 故障中
     */
    ERROR("2");

    private String code;

    UseStatusEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
