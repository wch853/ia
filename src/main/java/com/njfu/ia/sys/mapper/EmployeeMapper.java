package com.njfu.ia.sys.mapper;

import com.njfu.ia.sys.domain.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    /**
     * 查询员工列表
     *
     * @param employee empId empName
     * @return data
     */
    List<Employee> selectEmployees(Employee employee);

    /**
     * 插入员工信息
     *
     * @param employee empId empName empTel empPosition empAge empSex empPs
     * @return row count
     */
    int insertEmployee(Employee employee);

    /**
     * 修改员工信息
     *
     * @param employee empId empName empTel empPosition empAge empSex empPs
     * @return row count
     */
    int updateEmployee(Employee employee);

    /**
     * 删除员工信息
     *
     * @param employee empId
     * @return row count
     */
    int deleteEmployee(Employee employee);
}
