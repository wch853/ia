package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.Employee;
import com.njfu.wa.sys.utils.Result;

import java.util.List;

public interface EmployeeService {

    /**
     * 查询员工列表
     *
     * @param employee empId empName
     * @return data
     */
    List<Employee> getEmployees(Employee employee);

    /**
     * 新增员工信息
     *
     * @param employee empId empName empTel empPosition empAge empSex empPs
     * @return message
     */
    Result addEmployee(Employee employee);

    /**
     * 修改员工信息
     *
     * @param employee empId empName empTel empPosition empAge empSex empPs
     * @return message
     */
    Result modifyEmployee(Employee employee);

    /**
     * 删除员工信息
     *
     * @param employee empId
     * @return message
     */
    Result removeEmployee(Employee employee);
}
