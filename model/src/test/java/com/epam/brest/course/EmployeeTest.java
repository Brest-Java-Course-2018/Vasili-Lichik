package com.epam.brest.course;


import org.junit.Assert;

public class EmployeeTest {

    public static final String VASILI = "Vasili";

    @org.junit.Test
    public void getEmployeeName() {
        Employee employee = new Employee();
        employee.setEmployeeName(VASILI);
        Assert.assertTrue(employee.getEmployeeName().equals(VASILI));
        Assert.assertEquals(VASILI,employee.getEmployeeName());
    }
}