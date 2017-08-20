package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EmployeeMapperTest {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void selectEmployees() throws Exception {
        List<Employee> employees = employeeMapper.selectEmployees(new Employee());
        log.info("employees: {}", employees);
    }

    @Test
    public void insertEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setEmpId("e000");
        employee.setEmpName("test");
        employee.setEmpTel("test");
        employee.setEmpPosition("test");
        employee.setEmpAge(0);
        employee.setEmpSex("男");
        employee.setEmpPs("test");

        int res = employeeMapper.insertEmployee(employee);

        Assert.assertEquals(1, res);
    }

    @Test
    public void updateEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setEmpId("e001");
        employee.setEmpName("test");
        employee.setEmpTel("test");
        employee.setEmpPosition("test");
        employee.setEmpAge(0);
        employee.setEmpSex("男");
        employee.setEmpPs("test");

        int res = employeeMapper.updateEmployee(employee);

        Assert.assertEquals(1, res);
    }

    @Test
    public void deleteEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setEmpId("e001");

        int res = employeeMapper.deleteEmployee(employee);

        Assert.assertEquals(1, res);
    }

}