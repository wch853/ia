package com.njfu.ia.schedule;

import com.njfu.ia.process.domain.DataType;
import com.njfu.ia.process.domain.DownstreamRet;
import com.njfu.ia.process.domain.InformRet;
import com.njfu.ia.process.jms.JmsSender;
import com.njfu.ia.process.jms.consumer.handler.DataUpstreamHandler;
import com.njfu.ia.process.mapper.DataTypeMapper;
import com.njfu.ia.process.mapper.EndDeviceMapper;
import com.njfu.ia.process.mapper.UpstreamDataRecordMapper;
import com.njfu.ia.process.utils.Constants;
import com.njfu.ia.process.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

@Service
@EnableScheduling
public class TaskSchedule {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskSchedule.class);

    @Resource
    private DataTypeMapper dataTypeMapper;

    @Resource
    private EndDeviceMapper deviceMapper;

    @Resource
    private UpstreamDataRecordMapper upstreamDataRecordMapper;

    @Resource
    private JmsSender jmsSender;

    /**
     * 定时任务，更新数据类型映射关系
     */
    @Scheduled(fixedRate = Constants.UPDATE_DATA_TYPE_TIME)
    public void updateDataTypeMapTask() {
        DataType type = new DataType();
        type.setUseStatus(Constants.INUSE);
        // 查询使用中的数据类型
        List<DataType> dataTypes = dataTypeMapper.selectDataTypes(type);
        // 清空数据类型映射关系
        DataUpstreamHandler.DATA_TYPE_MAP.clear();
        for (DataType dataType : dataTypes) {
            // 重新加载数据类型映射关系
            DataUpstreamHandler.DATA_TYPE_MAP.put(dataType.getDataShortName(), dataType);
        }
    }

    @Scheduled(fixedRate = Constants.HEART_TASK_TIME)
    public void heartMonitorTask() {
        // 查询正在使用中的终端设备编号
        List<Integer> deviceIds = deviceMapper.selectDevice(null, Constants.INUSE);
        if (!CollectionUtils.isEmpty(deviceIds)) {
            // 查询指定时间间隔内有记录的设备编号（不需要心跳检测）
            List<Integer> hasRecordDeviceIds = upstreamDataRecordMapper.
                    selectHasRecordDevice(Constants.HEART_TASK_TIME / 1000, deviceIds);

            Iterator<Integer> iterator = deviceIds.iterator();
            while (iterator.hasNext()) {
                Integer deviceId = iterator.next();
                if (hasRecordDeviceIds.contains(deviceId)) {
                    iterator.remove();
                } else {
                    // 没有该终端的上行数据记录
                    DownstreamRet downstreamRet = new DownstreamRet();
                    downstreamRet.setMsgType(Constants.MESSAGE_DOWNSTREAM_HEART);
                    downstreamRet.setDeviceId(deviceId);
                    // 发送心跳指令
                    jmsSender.sendMessage(Constants.MESSAGE_DOWNSTREAM_HEART, JsonUtils.toJsonString(downstreamRet));
                }
            }

            if (deviceIds.size() != 0) {
                // 存在可能失联的终端，更新终端状态为故障中
                deviceMapper.batchUpdateDeviceUseStatus(deviceIds, Constants.ERROR);
                // 开启线程等待心跳反馈
                new Thread(new ListenHeart(deviceIds)).start();
            }
        }
    }

    /**
     * 心跳反馈处理线程
     */
    private class ListenHeart implements Runnable{

        private List<Integer> deviceIds;

        public ListenHeart(List<Integer> deviceIds) {
            this.deviceIds = deviceIds;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(Constants.HEART_BACK_LIMIT_TIME);
                List<Integer> onDeviceIds = deviceMapper.selectDevice(deviceIds, Constants.INUSE);
                if (onDeviceIds.size() < deviceIds.size()) {
                    // 存在失联的终端
                    Iterator<Integer> iterator = deviceIds.iterator();
                    if (iterator.hasNext()) {
                        Integer deviceId = iterator.next();
                        if (!onDeviceIds.contains(deviceId)) {
                            // 该终端未反馈心跳
                            InformRet informRet = new InformRet();
                            informRet.setMsgType(Constants.MESSAGE_INFORM_HEART);
                            informRet.setDeviceId(deviceId);
                            jmsSender.sendMessage(Constants.MESSAGE_INFORM_HEART, JsonUtils.toJsonString(informRet));
                        }
                    }
                }
            } catch (Exception e) {
                LOGGER.error("listen heart Exception.", e);
            }
        }
    }
}
