package com.njfu.wa.sys.service.impl;

import com.njfu.wa.sys.domain.Machine;
import com.njfu.wa.sys.mapper.MachineMapper;
import com.njfu.wa.sys.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineServiceImpl implements MachineService {

    @Autowired
    private MachineMapper machineMapper;

    @Override
    public List<Machine> getMachines() {
        return machineMapper.selectMachines();
    }
}
