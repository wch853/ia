package com.njfu.ia.process.service.impl;

import com.njfu.ia.process.domain.UpstreamRet;
import com.njfu.ia.process.jms.consumer.handler.DataUpstreamHandler;
import com.njfu.ia.process.jms.consumer.handler.HeartUpstreamHandler;
import com.njfu.ia.process.jms.consumer.handler.OnUpstreamHandler;
import com.njfu.ia.process.jms.consumer.handler.UpstreamHandler;
import com.njfu.ia.process.service.UpstreamService;
import com.njfu.ia.process.utils.Constants;
import com.njfu.ia.process.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UpstreamServiceImpl implements UpstreamService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpstreamServiceImpl.class);

    /**
     * 消费上线消息
     *
     * @param message messageText
     */
    @Override
    public void consumeUpstreamMessage(String message) {
        UpstreamRet ret = JsonUtils.toBean(message, UpstreamRet.class);
        if (null != ret) {
            Map<String, Object> retMap = this.convertRetToMap(ret.getData());
            Integer msgType = (Integer) retMap.get(Constants.RET_TYPE);
            try {
                // 调用处理器处理不同上行数据
                this.getUpstreamHandler(ret.getReceiveTime(), msgType, retMap).handleUpstream();
            } catch (NullPointerException e) {
                LOGGER.error("invalid msgType: {}", msgType);
            }
        }
    }

    /**
     * 将报文解析为key-value对
     *
     * @param data data
     * @return Map
     */
    private Map<String, Object> convertRetToMap(String data) {
        Map<String, Object> map = new HashMap<>();
        String[] dataSection = data.split(Constants.DATA_SEPERATOR);
        for (String pair : dataSection) {
            String[] split = pair.split(Constants.DATA_TYPE_VALUE_SEPERATOR);
            map.put(split[Constants.DATA_TYPE_IDX], split[Constants.DATA_VALUE_IDX]);
        }
        return map;
    }

    /**
     * 通过消息类型获取上行数据处理器
     *
     * @param receiveTime receiveTime
     * @param msgType     msgType
     * @param retMap      retMap
     * @return UpstreamHandler
     */
    private UpstreamHandler getUpstreamHandler(Date receiveTime, Integer msgType, Map<String, Object> retMap) {
        // 通过消息类型获取上行数据处理器
        UpstreamHandler handler = null;
        switch (msgType) {
            case Constants.MESSAGE_UPSTREAM_ON:
                handler = new OnUpstreamHandler(receiveTime, retMap);
                break;
            case Constants.MESSAGE_UPSTREAM_DATA:
                handler = new DataUpstreamHandler(receiveTime, retMap);
                break;
            case Constants.MESSAGE_UPSTREAM_HEART:
                handler = new HeartUpstreamHandler(receiveTime, retMap);
            default:
                break;
        }
        return handler;
    }
}
