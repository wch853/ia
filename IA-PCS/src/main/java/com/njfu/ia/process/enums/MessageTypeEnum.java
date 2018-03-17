package com.njfu.ia.process.enums;

import java.util.Arrays;
import java.util.List;

public enum MessageTypeEnum {

    /**
     * 上线消息
     */
    ON(0),

    /**
     * 数据消息
     */
    DATA(1),

    /**
     * 命令消息
     */
    COMMAND(2),

    /**
     * 心跳消息
     */
    HEART(3);

    /**
     * 消息代码
     */
    int code;

    /**
     * 有效消息类型
     */
    public static final List<Integer> VALID_MESSAGE_TYPE = Arrays.asList(ON.getCode(),
            DATA.getCode(), COMMAND.getCode(), HEART.getCode());

    MessageTypeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
