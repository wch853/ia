package com.njfu.ia.listener.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Json工具类
 * base on jackson
 */
public class JsonUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    /**
     * Object转json字符串
     *
     * @param object object
     * @return String
     */
    public static String toJsonString(Object object) {
        String json = null;
        try {
            json = MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOGGER.error("JsonUtils.toJsonString Exception", e);
        }
        return json;
    }

    /**
     * json格式字符串转Bean
     *
     * @param json  json
     * @param clazz Class.Type
     * @param <T>   T
     * @return T
     */
    public static <T> T toBean(String json, Class<T> clazz) {
        T t = null;
        try {
            t = MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            LOGGER.error("JsonUtils.toBean Exception", e);
        }
        return t;
    }
}
