package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;

import java.util.List;

/**
 * Department DAO Interface.
 */

public interface DepartmentDao {

    List<Department> getAllDepartment();

    Department getDepartmentById (Integer DepartmentId);

    Department addDepartment (Department department);

    void update (Department department);

    void deleteDepartmentById (Integer id);
 }
