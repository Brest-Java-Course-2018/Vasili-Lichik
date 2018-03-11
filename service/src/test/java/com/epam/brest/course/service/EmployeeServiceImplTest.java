package com.epam.brest.course.service;

import com.epam.brest.course.model.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml","classpath:service-test.xml", "classpath:dao.xml"})

public class EmployeeServiceImplTest {
    private static final int ID=1;
    private static final Integer SALARY=1800;

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void getEmployeeById() {
        Employee employee = employeeService.getEmployeeById(1);
        Assert.assertNotNull(employee);
    }

    @Test
    public void updateEmployeeSalary() {
        employeeService.updateEmployeeSalary(ID,SALARY);
        Employee employee = employeeService.getEmployeeById(ID);
        Assert.assertEquals(SALARY,employee.getSalary()); }
}