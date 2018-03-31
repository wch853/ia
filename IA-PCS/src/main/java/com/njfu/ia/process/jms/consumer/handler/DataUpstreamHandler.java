package com.njfu.ia.process.jms.consumer.handler;

import com.njfu.ia.process.domain.AlarmRecord;
import com.njfu.ia.process.domain.DataType;
import com.njfu.ia.process.domain.InformRet;
import com.njfu.ia.process.domain.UpstreamDataRecord;
import com.njfu.ia.process.jms.JmsSender;
import com.njfu.ia.process.mapper.AlarmRecordMapper;
import com.njfu.ia.process.mapper.UpstreamDataRecordMapper;
import com.njfu.ia.process.utils.Constants;
import com.njfu.ia.process.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 上行数据处理器
 */
public class DataUpstreamHandler extends UpstreamHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataUpstreamHandler.class);

    @Resource
    private UpstreamDataRecordMapper upstreamDataRecordMapper;

    @Resource
    private AlarmRecordMapper alarmRecordMapper;

    @Resource
    private JmsSender jmsSender;

    /**
     * 数据类型缩写-数据类型映射关系
     */
    public static final Map<String, DataType> DATA_TYPE_MAP = new ConcurrentHashMap<>();

    public DataUpstreamHandler(Date receiveTime, Map<String, Object> retMap) {
        super(receiveTime, retMap);
    }

    /**
     * 处理上行数据
     * 1.保存上行数据
     * 2.非法数据报警（保存与通知）
     */
    @Override
    public void handleUpstream() {
        // 数据上传时间
        Date receiveTime = super.getReceiveTime();
        // 数据
        Map<String, Object> retMap = super.getRetMap();
        // 获取来源终端设备编号
        Integer deviceId = (Integer) retMap.get(Constants.RET_ID);
        if (null == deviceId) {
            return;
        }

        List<UpstreamDataRecord> records = new ArrayList<>();
        UpstreamDataRecord record = new UpstreamDataRecord();
        record.setReceiveTime(receiveTime);
        record.setDeviceId(deviceId);

        for (Map.Entry<String, Object> entry : retMap.entrySet()) {
            // 数据类型缩写
            String dataShortName = entry.getKey();
            Double value = (Double) entry.getValue();
            DataType dataType = DATA_TYPE_MAP.get(dataShortName);

            // 上行数据组装
            record.setDataType(dataType.getId());
            record.setValue(value);
            records.add(record);

            if (value < dataType.getFloor() || value > dataType.getCeil()) {
                // 数据不在阈值范围内
                AlarmRecord alarmRecord = new AlarmRecord(record);
                // 保存报警数据
                alarmRecordMapper.insertAlarmRecord(alarmRecord);

                InformRet informRet = new InformRet();
                informRet.setMsgType(Constants.MESSAGE_INFORM_ALARM);
                informRet.setDeviceId(deviceId);
                informRet.setRecord(alarmRecord);
                // 报警通知
                jmsSender.sendMessage(Constants.MESSAGE_INFORM_ALARM, JsonUtils.toJsonString(informRet));
                LOGGER.info("end device {} upstream alarm record: {}", alarmRecord.getDeviceId(), JsonUtils.toJsonString(alarmRecord));
            }
        }

        if (!CollectionUtils.isEmpty(records)) {
            upstreamDataRecordMapper.batchInsertDataRecords(records);
        }
    }
}
