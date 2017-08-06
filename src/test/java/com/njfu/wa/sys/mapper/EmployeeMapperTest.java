package com.njfu.wa.sys.mapper;

import com.njfu.wa.sys.domain.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeMapperTest {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void selectEmployees() throws Exception {
        List<Employee> employees = employeeMapper.selectEmployees();
        log.info("employees : {}", employees);
    }

}