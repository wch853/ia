package com.njfu.wa.sys.util;

import com.njfu.wa.sys.Enums.MessageEnum;
import org.springframework.stereotype.Component;

/**
 * 消息工厂类，用于返回消息
 */
@Component
public class MessageFactory<T> {

    public MessageFactory() {
    }

    public Message successMessage(String message) {
        return new Message(MessageEnum.SUCCESS.getCode(), message);
    }

    public Message successMessage(String message, T data) {
        return new Message(MessageEnum.SUCCESS.getCode(), message, data);
    }

    public Message failMessage(String message) {
        return new Message(MessageEnum.FAIL.getCode(), message);
    }

    public Message failMessage(String message, T data) {
        return new Message(MessageEnum.FAIL.getCode(), message, data);
    }

}
