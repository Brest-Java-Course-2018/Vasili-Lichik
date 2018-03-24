package com.epam.brest.course.rest;

import com.epam.brest.course.model.dto.DepartmentDTO;
import com.epam.brest.course.service.DepartmentService;


import org.easymock.EasyMock;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;



import static org.easymock.EasyMock.*;
import java.util.Arrays;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
public class DepartmentControllerMockTest {

    private  static DepartmentDTO departmentDTO1;
    private  static DepartmentDTO departmentDTO2;

    @Autowired
    private DepartmentRestController departmentRestController;

    @Autowired
    private DepartmentService mockDepartmentService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        departmentDTO1 = new DepartmentDTO();
        departmentDTO1.setDepartmentId(1);
        departmentDTO1.setDepartmentName("departmentDTO1");

        departmentDTO2 = new DepartmentDTO();
        departmentDTO2.setDepartmentId(2);
        departmentDTO2.setDepartmentName("departmentDTO2");

        mockMvc = MockMvcBuilders.standaloneSetup(departmentRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
        EasyMock.reset(mockDepartmentService);
    }

    @After
    public  void tearDown(){
        verify(mockDepartmentService);
        reset(mockDepartmentService);
    }

    @Test
    public void getDepartments() throws Exception {
        EasyMock.expect(mockDepartmentService.getDepartmentDto())
                .andReturn(Arrays.asList(departmentDTO1,departmentDTO2));

        EasyMock.replay(mockDepartmentService);

        mockMvc.perform(
                get("/departments")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(
                        MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].departmentId", Matchers.is(1)))
                .andExpect(jsonPath("$[0].departmentName", Matchers.is("departmentDTO1")))
                .andExpect(jsonPath("$[1].departmentId", Matchers.is(2)))
                .andExpect(jsonPath("$[1].departmentName", Matchers.is("departmentDTO2")));
    }
}

