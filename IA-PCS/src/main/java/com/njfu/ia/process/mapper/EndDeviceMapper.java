package com.njfu.ia.process.mapper;

import com.njfu.ia.process.domain.EndDevice;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EndDeviceMapper {

    /**
     * 通过MAC地址查询对应终端的网络编号
     *
     * @param macAddress macAddress
     * @return EndDevice
     */
    EndDevice selectDeviceByMac(String macAddress);
}
