package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.Machine;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MachineMapper {

    /**
     * 获取机械列表
     *
     * @param machine machineId machineType blockId useStatus
     * @return data
     */
    List<Machine> selectMachines(Machine machine);

    /**
     * 新增机械信息
     *
     * @param machine machineId machineType blockId useStatus
     * @return row count
     */
    int insertMachine(Machine machine);

    /**
     * 修改机械信息
     *
     * @param machine machineId machineType blockId useStatus
     * @return row count
     */
    int updateMachine(Machine machine);

    /**
     * 删除机械信息
     *
     * @param machine machineId
     * @return row count
     */
    int deleteMachine(Machine machine);
}
