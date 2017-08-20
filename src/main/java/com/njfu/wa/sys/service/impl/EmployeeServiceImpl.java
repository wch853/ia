package com.njfu.wa.sys.service.impl;

import com.njfu.wa.sys.domain.Employee;
import com.njfu.wa.sys.mapper.EmployeeMapper;
import com.njfu.wa.sys.service.EmployeeService;
import com.njfu.wa.sys.util.Message;
import com.njfu.wa.sys.util.MessageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private MessageFactory messageFactory;

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
     * @return message
     */
    @Override
    public Message addEmployee(Employee employee) {
        this.convertNull(employee);

        int res = employeeMapper.insertEmployee(employee);

        if (res == 0) {
            return messageFactory.failMessage("新增员工信息失败，请检查新增编号是否存在！");
        }

        return messageFactory.successMessage("新增员工信息成功！");
    }

    /**
     * 修改员工信息
     *
     * @param employee empId empName empTel empPosition empAge empSex empPs
     * @return message
     */
    @Override
    public Message modifyEmployee(Employee employee) {
        this.convertNull(employee);

        int res = employeeMapper.updateEmployee(employee);

        if (res == 0) {
            return messageFactory.failMessage("修改员工信息失败！");
        }

        return messageFactory.successMessage("修改员工信息成功！");
    }

    /**
     * 删除员工信息
     *
     * @param employee empId
     * @return message
     */
    @Override
    public Message removeEmployee(Employee employee) {
        int res = employeeMapper.deleteEmployee(employee);

        if (res == 0) {
            return messageFactory.failMessage("删除员工信息失败！");
        }

        return messageFactory.successMessage("删除员工信息成功！");
    }

    /**
     * 若empPosition、empSex、empPs为空字符串，转为null
     *
     * @param employee empPosition empSex empPs
     */
    private void convertNull(Employee employee) {
        if ("".equals(employee.getEmpPosition())) {
            employee.setEmpPosition(null);
        }
        if ("".equals(employee.getEmpSex())) {
            employee.setEmpSex(null);
        }
        if ("".equals(employee.getEmpPs())) {
            employee.setEmpPs(null);
        }
    }
}
