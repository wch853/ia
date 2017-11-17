package com.njfu.wa.sys.service.impl;

import com.njfu.wa.sys.domain.Block;
import com.njfu.wa.sys.domain.Machine;
import com.njfu.wa.sys.utils.Result;
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
     * @return message
     */
    @Override
    public Result addMachine(Machine machine, Block block) {
        this.convertNull(machine, block);

        int res = machineMapper.insertMachine(machine);

        if (res == 0) {
            return Result.fail("新增机械信息失败，请检查新增编号是否存在！");
        }

        return Result.success("新增机械信息成功！");
    }

    /**
     * 修改机械信息
     *
     * @param machine machineId machineType useStatus machinePs
     * @param block   blockId
     * @return message
     */
    @Override
    public Result modifyMachine(Machine machine, Block block) {
        this.convertNull(machine, block);

        int res = machineMapper.updateMachine(machine);

        if (res == 0) {
            return Result.fail("修改机械信息失败！");
        }

        return Result.success("修改机械信息成功！");
    }

    /**
     * 删除机械信息
     *
     * @param machine machineId
     * @return message
     */
    @Override
    public Result removeMachine(Machine machine) {
        int res = machineMapper.deleteMachine(machine);

        if (res == 0) {
            return Result.fail("删除机械信息失败！");
        }

        return Result.success("删除机械信息成功！");
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
