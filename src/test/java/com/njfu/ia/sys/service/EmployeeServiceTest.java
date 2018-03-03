package com.njfu.ia.sys.service;

import com.njfu.ia.sys.domain.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EmployeeServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceTest.class);

    @Resource
    private EmployeeService employeeService;

    @Test
    public void getEmployees() throws Exception {
        List<Employee> employees = employeeService.getEmployees(new Employee());
        LOGGER.info("employees: {}", employees);
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

        try {
            employeeService.addEmployee(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        try {
            employeeService.modifyEmployee(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void removeEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setEmpId("e001");

        try {
            employeeService.removeEmployee(employee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}