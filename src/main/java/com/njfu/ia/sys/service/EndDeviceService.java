package com.njfu.ia.sys.service;

import com.njfu.ia.sys.domain.EndDevice;

import java.util.List;

public interface EndDeviceService {

    /**
     * 查询终端设备信息
     *
     * @param endDevice
     * @return
     */
    List<EndDevice> queryEndDevices(EndDevice endDevice);
}
