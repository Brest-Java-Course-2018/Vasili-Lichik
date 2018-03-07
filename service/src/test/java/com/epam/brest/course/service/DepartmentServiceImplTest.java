package com.epam.brest.course.service;

import com.epam.brest.course.model.Department;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml","classpath:service-test.xml", "classpath:dao.xml"})


public class DepartmentServiceImplTest {
    private static final int ID=1;
    private static final String DESCRIPTION="Academic department";
    @Autowired
    private DepartmentService departmentService;
    @Test
    public void getDepartmentById() {
        departmentService.updateDepartmentDescription(ID,DESCRIPTION);
        Department department = departmentService.getDepartmentById(ID);
        Assert.assertEquals(DESCRIPTION,department.getDescription());
    }
}