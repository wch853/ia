package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.Machine;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MachineMapper {

    List<Machine> selectMachines();
}
