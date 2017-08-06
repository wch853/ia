package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    List<Employee> selectEmployees();
}
