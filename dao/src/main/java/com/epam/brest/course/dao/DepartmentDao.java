package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.model.dto.DepartmentDTO;

import java.util.Collection;
import java.util.List;

/**
 * Department DAO Interface.
 */
public interface DepartmentDao {

    List<Department> getDepartments();

    Department getDepartmentById(Integer departmentId);

    Department addDepartment(Department department);

    void updateDepartment(Department department);

    void deleteDepartmentById(Integer id);

    Collection<DepartmentDTO> getDepartmentDTOs();


}
