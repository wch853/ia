package com.njfu.wa.sys.service;

import com.njfu.wa.sys.domain.Employee;
import com.njfu.wa.sys.domain.util.Result;
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
public class EmployeeServiceTest {

    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceTest.class);

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void getEmployees() throws Exception {
        List<Employee> employees = employeeService.getEmployees(new Employee());
        log.info("employees: {}", employees);
    }

    @Test
    public void addEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setEmpId("e000");
        employee.setEmpName("test");
        employee.setEmpTel("test");
        employee.setEmpPosition("test");
        employee.setEmpAge(0);
        employee.setEmpSex("男");
        employee.setEmpPs("test");

        Result result = employeeService.addEmployee(employee);
        log.info("result: {}", result);
    }

    @Test
    public void modifyEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setEmpId("e001");
        employee.setEmpName("test");
        employee.setEmpTel("test");
        employee.setEmpPosition("test");
        employee.setEmpAge(0);
        employee.setEmpSex("男");
        employee.setEmpPs("test");

        Result result = employeeService.modifyEmployee(employee);
        log.info("result: {}", result);
    }

    @Test
    public void removeEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setEmpId("e001");

        Result result = employeeService.removeEmployee(employee);
        log.info("result: {}", result);
    }

}