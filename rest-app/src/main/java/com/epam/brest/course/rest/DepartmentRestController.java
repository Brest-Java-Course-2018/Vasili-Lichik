package com.epam.brest.course.rest;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.model.dto.DepartmentDTO;
import com.epam.brest.course.service.DepartmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class DepartmentRestController {

    private static  final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private DepartmentService departmentService;


    @GetMapping(value = "/departments")
    Collection<DepartmentDTO> departments() {
        LOGGER.debug("departments()");
        return  departmentService.getDepartmentDto();
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping(value = "/department/{id}")
    Department department(@PathVariable Integer id) {
        LOGGER.debug("department()");
        return  departmentService.getDepartmentById(id);
    }

    @ResponseStatus(HttpStatus.  CREATED)
    @PostMapping (value ="/departments")
    Department addDepartment (@RequestBody Department department){
        LOGGER.debug("addDepartment({})",department);
     return  departmentService.addDepartment(department);
    }

    @ResponseStatus(HttpStatus.FOUND)
    @DeleteMapping (value ="/department/{id}")
    void deleteDepartment (@PathVariable Integer id){
        LOGGER.debug("deleteDepartment({})",id);
        departmentService.deleteDepartmentById(id);

    }
}

//curl -v -X POST -d '{"departmentId":5,"departmentName":"HRnew","description":"HR Department new"}' -H "Content-Type: application/json" localhost:8081/departments
