package com.njfu.ia.sys.service;

import com.njfu.ia.sys.domain.Block;
import com.njfu.ia.sys.domain.Machine;

import java.util.List;

public interface MachineService {

    /**
     * 获取机械列表
     *
     * @param machine machineId machineType useStatus
     * @param block   blockId
     * @return data
     */
    List<Machine> queryMachines(Machine machine, Block block);

    /**
     * 新增机械信息
     *
     * @param machine machineId machineType useStatus machinePs
     * @param block   blockId
     */
    void addMachine(Machine machine, Block block);

    /**
     * 修改机械信息
     *
     * @param machine machineId machineType useStatus machinePs
     * @param block   blockId
     */
    void modifyMachine(Machine machine, Block block);

    /**
     * 删除机械信息
     *
     * @param machine machineId
     */
    void removeMachine(Machine machine);
}
