package com.njfu.ia.sys.service.impl;

import com.njfu.ia.sys.domain.Employee;
import com.njfu.ia.sys.exception.BusinessException;
import com.njfu.ia.sys.mapper.EmployeeMapper;
import com.njfu.ia.sys.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    /**
     * 查询员工列表
     *
     * @param employee empId empName
     * @return data
     */
    @Override
    public List<Employee> getEmployees(Employee employee) {
        return employeeMapper.selectEmployees(employee);
    }

    /**
     * 新增员工信息
     *
     * @param employee empId empName empTel empPosition empAge empSex empPs
     */
    @Override
    public void addEmployee(Employee employee) {
        this.convertNull(employee);
        int res = employeeMapper.insertEmployee(employee);
        if (res <= 0) {
            throw new BusinessException("新增员工信息失败");
        }
    }

    /**
     * 修改员工信息
     *
     * @param employee empId empName empTel empPosition empAge empSex empPs
     */
    @Override
    public void modifyEmployee(Employee employee) {
        this.convertNull(employee);
        int res = employeeMapper.updateEmployee(employee);
        if (res == 0) {
            throw new BusinessException("修改员工信息失败！");
        }
    }

    /**
     * 删除员工信息
     *
     * @param employee empId
     */
    @Override
    public void removeEmployee(Employee employee) {
        int res = employeeMapper.deleteEmployee(employee);
        if (res == 0) {
            throw new BusinessException("删除员工信息失败！");
        }
    }

    /**
     * 若empPosition、empSex、empMail、empPs为空字符串，转为null
     *
     * @param employee empPosition empSex empMail empPs
     */
    private void convertNull(Employee employee) {
        if ("".equals(employee.getEmpPosition())) {
            employee.setEmpPosition(null);
        }
        if ("".equals(employee.getEmpSex())) {
            employee.setEmpSex(null);
        }
        if ("".equals(employee.getEmpMail())) {
            employee.setEmpMail(null);
        }
        if ("".equals(employee.getEmpPs())) {
            employee.setEmpPs(null);
        }
    }
}
