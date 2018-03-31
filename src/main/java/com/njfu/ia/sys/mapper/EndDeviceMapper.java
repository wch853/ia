package com.njfu.ia.sys.mapper;

import com.njfu.ia.sys.domain.EndDevice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EndDeviceMapper {

    /**
     * 查询终端设备信息
     *
     * @param endDevice
     * @return
     */
    List<EndDevice> selectEndDevices(EndDevice endDevice);

}
