package com.njfu.wa.sys.service.impl;

import com.njfu.wa.sys.domain.Employee;
import com.njfu.wa.sys.mapper.EmployeeMapper;
import com.njfu.wa.sys.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getEmployees() {
        return employeeMapper.selectEmployees();
    }
}
