package com.epam.brest.course.service;

import com.epam.brest.course.model.Department;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface DepartmentService {

    Department getDepartmentById (Integer departmentId);

    void updateDepartmentDescription (Integer departmentId,String description);

    Collection<Department> getDepartments();
}
