package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EmployeeMapper {

    List<Employee> selectEmployees();
}
