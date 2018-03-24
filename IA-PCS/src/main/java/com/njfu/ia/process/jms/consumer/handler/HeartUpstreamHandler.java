package com.njfu.ia.process.jms.consumer.handler;

import com.njfu.ia.process.domain.EndDevice;
import com.njfu.ia.process.mapper.EndDeviceMapper;
import com.njfu.ia.process.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * 心跳上行数据处理器
 */
public class HeartUpstreamHandler extends UpstreamHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeartUpstreamHandler.class);

    @Resource
    private EndDeviceMapper endDeviceMapper;

    public HeartUpstreamHandler(Date receiveTime, Map<String, Object> retMap) {
        super(receiveTime, retMap);
    }

    /**
     * 处理心跳反馈
     * 更新终端使用中状态 tp:3|id:1
     */
    @Override
    public void handleUpstream() {
        Map<String, Object> retMap = super.getRetMap();
        Integer deviceId = (Integer) retMap.get(Constants.RET_ID);
        if (null != deviceId) {
            EndDevice device = new EndDevice();
            device.setId(deviceId);
            device.setUseStatus(Constants.INUSE);
            int count = endDeviceMapper.updateDevice(device);
            if (count <= 0) {
                LOGGER.error("heart back update end device {} failed", deviceId);
            }
        }
    }
}
