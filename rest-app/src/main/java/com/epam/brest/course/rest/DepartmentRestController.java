package com.epam.brest.course.rest;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.service.DepartmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class DepartmentRestController {

    private static  final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private DepartmentService departmentService;


    @GetMapping(value = "/departments")
    Collection<Department> departments() {
        LOGGER.debug("departments()");
        return  departmentService.getDepartments();
    }

    @GetMapping(value = "/department/{id}")
    Department department(@PathVariable Integer id) {
        LOGGER.debug("department()");
        return  departmentService.getDepartmentById(id);
    }
}
