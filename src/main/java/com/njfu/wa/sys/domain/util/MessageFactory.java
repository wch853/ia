package com.njfu.wa.sys.domain.util;

import com.njfu.wa.sys.enums.MessageEnum;
import org.springframework.stereotype.Service;

/**
 * 消息工厂类，用于返回消息
 */
@Service
public class MessageFactory<T> {

    public MessageFactory() {
    }

    public Message successMessage(String message) {
        return new Message(MessageEnum.SUCCESS.getCode(), message);
    }

    public Message failMessage(String message) {
        return new Message(MessageEnum.FAIL.getCode(), message);
    }

    public Message dataMessage(T data) {
        return new Message<>(MessageEnum.DATA.getCode(), MessageEnum.DATA.getInfo(), data);
    }

}
