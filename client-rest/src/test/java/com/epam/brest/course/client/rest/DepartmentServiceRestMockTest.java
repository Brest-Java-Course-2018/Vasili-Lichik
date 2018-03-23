package com.epam.brest.course.client.rest;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.model.dto.DepartmentDTO;
import com.epam.brest.course.service.DepartmentService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.easymock.EasyMock.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-configuration")
public class DepartmentServiceRestMockTest {

    private  static DepartmentDTO departmentDTO1;
    private  static DepartmentDTO departmentDTO2;
    private static Department department;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    private RestTemplate mockRestTemplate;

    @Before
    public void setUp() {
        departmentDTO1 = new DepartmentDTO();
        departmentDTO1.setDepartmentId(1);
        departmentDTO1.setDepartmentName("departmentDTO1");

        departmentDTO2 = new DepartmentDTO();
        departmentDTO2.setDepartmentId(2);
        departmentDTO2.setDepartmentName("departmentDTO2");

        department = new Department("department", "department New");
        department.setDepartmentId(3);
    }

    @After
    public void tearDown(){
        reset (mockRestTemplate);
    }

    @Test
    public void getAllDepartments(){
        List departments = Arrays.asList(departmentDTO1,departmentDTO2);
        ResponseEntity entity = new ResponseEntity<>(departments, HttpStatus.OK);
        expect(mockRestTemplate.getForEntity(anyString(),anyObject()))
                .andReturn(entity);
        replay(mockRestTemplate);



        Collection <DepartmentDTO> results = departmentService.getDepartmentDto();
        Assert.assertNotNull(results);
        Assert.assertEquals(2,results.size());
    }

    @Test
    public void getDepartmentById(){
        ResponseEntity entity = new ResponseEntity<>(department,HttpStatus.OK);
        expect(mockRestTemplate.getForEntity(anyString(),anyObject())).andReturn(entity);
        replay(mockRestTemplate);


        Department result = departmentService.getDepartmentById(3);



        Assert.assertNotNull(result);



    }
}
