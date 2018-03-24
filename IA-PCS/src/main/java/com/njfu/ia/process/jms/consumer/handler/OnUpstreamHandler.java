package com.njfu.ia.process.jms.consumer.handler;

import com.njfu.ia.process.domain.DownstreamRet;
import com.njfu.ia.process.domain.EndDevice;
import com.njfu.ia.process.domain.InformRet;
import com.njfu.ia.process.jms.JmsSender;
import com.njfu.ia.process.mapper.EndDeviceMapper;
import com.njfu.ia.process.utils.Constants;
import com.njfu.ia.process.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * 上线上行数据处理器
 */
public class OnUpstreamHandler extends UpstreamHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(OnUpstreamHandler.class);

    @Resource
    private EndDeviceMapper endDeviceMapper;

    @Resource
    private JmsSender jmsSender;

    public OnUpstreamHandler(Date receiveTime, Map<String, Object> retMap) {
        super(receiveTime, retMap);
    }

    /**
     * 上线消息处理 tp:0|mac:00-12-4B-00-03-98-A1-AB
     * 1.更新上线状态
     * 2.分配设备编号 tp:0|mac:00-12-4B-00-03-98-A1-AB|id:1
     * 3.推送上线消息
     */
    @Override
    public void handleUpstream() {
        Map<String, Object> retMap = super.getRetMap();
        // 获取mac地址
        String mac = (String) retMap.get(Constants.RET_MAC);
        EndDevice device = endDeviceMapper.selectDeviceByMac(mac);
        if (null == device) {
            return;
        }
        // 获取设备编号
        Integer deviceId = device.getId();
        Integer useStatus = device.getUseStatus();
        if (useStatus != Constants.UNUSE) {
            // 终端未处于不使用
            device.setUseStatus(Constants.INUSE);
            // 更新终端信息
            endDeviceMapper.updateDevice(device);

            // 分配设备编号
            DownstreamRet downstreamRet = new DownstreamRet();
            downstreamRet.setMsgType(Constants.MESSAGE_DOWNSTREAM_ON);
            downstreamRet.setMac(mac);
            downstreamRet.setDeviceId(deviceId);
            // 分配终端设备编号
            jmsSender.sendMessage(Constants.MESSAGE_DOWNSTREAM_ON, JsonUtils.toJsonString(downstreamRet));

            InformRet informRet = new InformRet();
            informRet.setMsgType(Constants.MESSAGE_INFORM_ON);
            informRet.setDeviceId(deviceId);
            // 推送上线消息
            jmsSender.sendMessage(Constants.MESSAGE_INFORM_ON, JsonUtils.toJsonString(informRet));
            LOGGER.info("end device {} on", deviceId);
        }
    }
}
