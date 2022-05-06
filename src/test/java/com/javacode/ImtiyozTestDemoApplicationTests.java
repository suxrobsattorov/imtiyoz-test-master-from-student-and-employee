package com.javacode;

import com.javacode.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ImtiyozTestDemoApplicationTests {

    @Autowired
    private EmployeeService employeeService;
    @Test
    void contextLoads() {

    }

}
