package com.njfu.ia.process.service.impl;

import com.njfu.ia.process.domain.DataType;
import com.njfu.ia.process.domain.EndDevice;
import com.njfu.ia.process.domain.UploadDataRecord;
import com.njfu.ia.process.domain.UploadRet;
import com.njfu.ia.process.enums.MessageTypeEnum;
import com.njfu.ia.process.enums.UseStatusEnum;
import com.njfu.ia.process.mapper.DataTypeMapper;
import com.njfu.ia.process.mapper.EndDeviceMapper;
import com.njfu.ia.process.mapper.UploadDataRecordMapper;
import com.njfu.ia.process.service.UploadDataService;
import com.njfu.ia.process.utils.Constants;
import com.njfu.ia.process.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.jms.Topic;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@EnableScheduling
public class UploadDataServiceImpl implements UploadDataService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadDataServiceImpl.class);

    /**
     * 数据类型缩写-数据类型编号映射关系
     */
    private static final Map<String, Integer> DATA_TYPE_MAP = new ConcurrentHashMap<>();

    @Resource
    private DataTypeMapper dataTypeMapper;

    @Resource
    private EndDeviceMapper endDeviceMapper;

    @Resource
    private UploadDataRecordMapper uploadDataRecordMapper;

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource
    private Topic downloadTopic;

    /**
     * 消费上行数据
     *
     * @param message messageText
     */
    @Override
    public void consumeUploadData(String message) {
        // 解析上行数据报文
        UploadRet uploadRet = JsonUtils.toBean(message, UploadRet.class);
        if (null != uploadRet) {
            handleUploadRet(uploadRet);
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
     * 处理上行数据报文
     *
     * @param ret ret
     */
    private void handleUploadRet(UploadRet ret) {
        // 数据上传时间
        Date receiveTime = ret.getReceiveTime();
        // 数据内容
        String data = ret.getData();
        /*
          tp:0|mac:5C-93-A2-FE-2E-BA
          tp:1|id:1|t:21.8|h:45.7|l:1890|co2:650
          tp:2|id:1|ir:15|cid: 983dec40639bd453ad3844528e69d92d
         */
        // 将上行数据内容转为键值对
        Map<String, Object> dataMap = convertRetToMap(data);
        // 消息类型
        Integer messageType = (Integer) dataMap.get(Constants.RET_TYPE);
        if (MessageTypeEnum.VALID_MESSAGE_TYPE.contains(messageType)) {
            // 消息类型有效
            if (MessageTypeEnum.DATA.getCode() == messageType) {
                // 上行数据消息
                handleUploadData(receiveTime, dataMap);
            } else if (MessageTypeEnum.ON.getCode() == messageType) {
                // 心跳数据消息
                // 获取mac地址
                String macAddress = (String) dataMap.get(Constants.RET_MAC);
                // 查询该终端是否已经注册
                EndDevice device = endDeviceMapper.selectDeviceByMac(macAddress);
                if (null != device) {
                    if (UseStatusEnum.INUSE.getCode() != device.getUseStatus()) {

                    }
                }
                jmsMessagingTemplate.convertAndSend(this.downloadTopic);
            }
        }
    }

    /**
     * 处理上行数据
     *
     * @param receiveTime receiveTime
     * @param dataMap     dataMap
     */
    private void handleUploadData(Date receiveTime, Map<String, Object> dataMap) {
        List<UploadDataRecord> uploadDataRecords = new ArrayList<>();
        UploadDataRecord uploadDataRecord = new UploadDataRecord();
        Integer endDeviceId = (Integer) dataMap.get(Constants.RET_ID);
        if (null != endDeviceId) {
            uploadDataRecord.setEndDeviceId(endDeviceId);
            uploadDataRecord.setReceiveTime(receiveTime);

            // 组装落地数据参数
            for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
                // 获取数据类型编号
                Integer dataTypeId = DATA_TYPE_MAP.get(entry.getKey());
                // 获取数据值
                Double value = (Double) entry.getValue();

                uploadDataRecord.setDataType(dataTypeId);
                uploadDataRecord.setValue(value);
                uploadDataRecords.add(uploadDataRecord);
            }

            if (!CollectionUtils.isEmpty(uploadDataRecords)) {
                // 上行数据入库
                int count = uploadDataRecordMapper.batchInsertDataRecords(uploadDataRecords);
                if (count <= 0) {
                    LOGGER.error("batch insert upload data record failed: {}", JsonUtils.toJsonString(dataMap));
                }
            }
        } else {
            LOGGER.info("invalid data for missing deviceId: {}", JsonUtils.toJsonString(dataMap));
        }
    }

    /**
     * 定时任务，更新数据类型映射关系
     */
    @Scheduled(fixedRate = Constants.UPDATE_DATA_TYPE_TIME)
    public void updateDataTypeMap() {
        List<DataType> dataTypes = dataTypeMapper.selectDataTypes();
        // 清空数据类型映射关系
        DATA_TYPE_MAP.clear();
        for (DataType dataType : dataTypes) {
            // 重新加载数据类型映射关系
            DATA_TYPE_MAP.put(dataType.getDataShortName(), dataType.getId());
        }
    }
}
