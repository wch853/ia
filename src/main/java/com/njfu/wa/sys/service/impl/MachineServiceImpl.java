package com.njfu.wa.sys.service.impl;

import com.njfu.wa.sys.domain.Block;
import com.njfu.wa.sys.domain.Machine;
import com.njfu.wa.sys.exception.BusinessException;
import com.njfu.wa.sys.mapper.MachineMapper;
import com.njfu.wa.sys.service.MachineService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MachineServiceImpl implements MachineService {

    @Resource
    private MachineMapper machineMapper;

    /**
     * 获取机械列表
     *
     * @param machine machineId machineType useStatus
     * @param block   blockId
     * @return data
     */
    @Override
    public List<Machine> getMachines(Machine machine, Block block) {
        machine.setBlock(block);
        return machineMapper.selectMachines(machine);
    }

    /**
     * 新增机械信息
     *
     * @param machine machineId machineType useStatus machinePs
     * @param block   blockId
     */
    @Override
    public void addMachine(Machine machine, Block block) {
        this.convertNull(machine, block);
        int res = machineMapper.insertMachine(machine);
        if (res <= 0) {
            throw new BusinessException("新增机械信息失败，请检查新增编号是否存在！");
        }
    }

    /**
     * 修改机械信息
     *
     * @param machine machineId machineType useStatus machinePs
     * @param block   blockId
     */
    @Override
    public void modifyMachine(Machine machine, Block block) {
        this.convertNull(machine, block);
        int res = machineMapper.updateMachine(machine);
        if (res <= 0) {
            throw new BusinessException("修改机械信息失败！");
        }
    }

    /**
     * 删除机械信息
     *
     * @param machine machineId
     */
    @Override
    public void removeMachine(Machine machine) {
        int res = machineMapper.deleteMachine(machine);
        if (res <= 0) {
            throw new BusinessException("删除机械信息失败！");
        }
    }

    /**
     * 若machinePs、blockId为空字符串，转为null
     *
     * @param machine machinePs
     * @param block   blockId
     */
    private void convertNull(Machine machine, Block block) {
        if ("".equals(machine.getMachinePs())) {
            machine.setMachinePs(null);
        }

        if (!"".equals(block.getBlockId())) {
            machine.setBlock(block);
        } else {
            machine.setBlock(new Block());
        }
    }
}
