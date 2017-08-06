package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.Machine;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MachineMapper {

    List<Machine> selectMachines();
}
