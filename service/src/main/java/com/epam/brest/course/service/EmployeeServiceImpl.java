package com.epam.brest.course.service;


import com.epam.brest.course.dao.EmployeeDao;
import com.epam.brest.course.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LogManager.getLogger();

    private EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public Employee getEmployeeById(Integer employeeId) {
        LOGGER.debug("getEmployeeById({})",employeeId);
        return employeeDao.getEmployeeById(employeeId);
    }

    @Override
    public void updateEmployeeSalary(Integer employeeId, Integer salary) {
        LOGGER.debug("updateEmployeeSalary({}{})",employeeId,salary);
        Employee employee = employeeDao.getEmployeeById(employeeId);
        employee.setSalary(salary);
        employeeDao.updateEmployee(employee);
    }
}
