package com.njfu.ia.sys.service.impl;

import com.njfu.ia.sys.domain.EndDevice;
import com.njfu.ia.sys.mapper.EndDeviceMapper;
import com.njfu.ia.sys.service.EndDeviceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EndDeviceServiceImpl implements EndDeviceService {

    @Resource
    private EndDeviceMapper endDeviceMapper;

    /**
     * 查询终端设备信息
     *
     * @param endDevice
     * @return
     */
    @Override
    public List<EndDevice> queryEndDevices(EndDevice endDevice) {
        return endDeviceMapper.selectEndDevices(endDevice);
    }
}
