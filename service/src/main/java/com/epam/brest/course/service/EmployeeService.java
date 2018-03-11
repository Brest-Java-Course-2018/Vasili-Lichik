package com.epam.brest.course.service;

import com.epam.brest.course.model.Employee;

public interface EmployeeService {

    Employee getEmployeeById(Integer employeeId);

    void updateEmployeeSalary (Integer employeeId, Integer salary);


}