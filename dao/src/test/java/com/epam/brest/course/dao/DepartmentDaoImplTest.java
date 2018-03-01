package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-db-spring.xml",
    "classpath*:test-dao.xml"})

public class DepartmentDaoImplTest {

    @Autowired
    DepartmentDao departmentDao;

    @Test
    public void getAllDepartment() {
        System.out.println(departmentDao);
        List<Department> departments = departmentDao.getAllDepartment();
        Assert.assertFalse(departments.isEmpty());
    }

    @Test
    public void getDepartmentById() {
        Department department = departmentDao.getDepartmentById(1);
        Assert.assertNotNull(department);
        Assert.assertTrue(department.getDepartmentId().equals(1));
        Assert.assertTrue(department.getDepartmentName().equals("Distribution"));
        Assert.assertTrue(department.getDescription().equals("Distribution Department"));
    }

    @Test
    public void addDepartment() {
        Department departmentToAdd = new Department();
        departmentToAdd.setDepartmentName("Java dev");
        departmentToAdd.setDescription("Java developers");
        Department departmentGet;
        departmentGet=departmentDao.addDepartment(departmentToAdd);
        Assert.assertNotNull(departmentGet);
        Assert.assertEquals(departmentToAdd.getDepartmentName(),departmentGet.getDepartmentName());
        Assert.assertEquals(departmentToAdd.getDescription(),departmentGet.getDescription());
    }

    @Test
    public void updateDepartment() {
        Department departmentToAdd = new Department();
        departmentToAdd.setDepartmentId(1);
        departmentToAdd.setDepartmentName("Java dev");
        departmentToAdd.setDescription("Java developers");
        departmentDao.updateDepartment(departmentToAdd);
        Department departmentGet = departmentDao.getDepartmentById(1);
        Assert.assertNotNull(departmentGet);
        Assert.assertEquals(departmentToAdd.getDepartmentId(), departmentGet.getDepartmentId());
        Assert.assertEquals(departmentToAdd.getDepartmentName(), departmentGet.getDepartmentName());
        Assert.assertEquals(departmentToAdd.getDescription(), departmentGet.getDescription());

    }


    public void deleteDepartmentById () {
        departmentDao.deleteDepartmentById(1);
        Department departmentGet = departmentDao.getDepartmentById(1);
        Assert.assertNull(departmentGet);
    }

}