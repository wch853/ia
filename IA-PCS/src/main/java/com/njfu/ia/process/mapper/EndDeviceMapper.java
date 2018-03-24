package com.njfu.ia.process.mapper;

import com.njfu.ia.process.domain.EndDevice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EndDeviceMapper {

    /**
     * 通过MAC地址查询对应终端的网络编号
     *
     * @param macAddress macAddress
     * @return EndDevice
     */
    EndDevice selectDeviceByMac(String macAddress);

    /**
     * 更新终端信息
     *
     * @param endDevice endDevice
     * @return count
     */
    int updateDevice(EndDevice endDevice);

    /**
     * 查询设备编号
     *
     * @param deviceIds deviceIds
     * @param useStatus useStatus
     * @return device id list
     */
    List<Integer> selectDevice(@Param("deviceIds") List<Integer> deviceIds, @Param("useStatus") Integer useStatus);

    /**
     * 批量更新终端使用状态
     *
     * @param deviceIds deviceIds
     * @param useStatus useStatus
     * @return count
     */
    int batchUpdateDeviceUseStatus(@Param("deviceIds") List<Integer> deviceIds, @Param("useStatus") Integer useStatus);
}
