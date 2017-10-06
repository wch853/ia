package com.njfu.wa.sys.domain.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JsonUtil {

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

    /**
     * 对象转字符串
     *
     * @param object object
     * @return String
     */
    public static String toJsonString(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * 字符串转对象
     * @param text text
     * @return Object
     */
    public static Object parseObject(String text) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.readValue(text, Object.class);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
