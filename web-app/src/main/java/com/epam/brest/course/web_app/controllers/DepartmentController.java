package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.model.dto.DepartmentDTO;
import com.epam.brest.course.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

/**
 * Department controller.
 */
@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * Goto departments page.
     *
     * @return view name
     */
    @GetMapping(value = "/departments")
    public final String departments(Model model) {
        Collection<DepartmentDTO> departmentsDto = departmentService.getDepartmentDto();
        model.addAttribute("departments", departmentsDto);
        return "departments";
    }

    /**
     * Goto department page.
     *
     * @return view name
     */
    @GetMapping(value = "/department/{id}")
    public final String getDepartmentById(@PathVariable Integer id, Model model) {
        Department department = departmentService.getDepartmentById(id);
        model.addAttribute("department", department);
        model.addAttribute("isNew",false);
        return "department";
    }

    /**
     * Goto department page.
     *
     * @return view name
     */
    @GetMapping(value = "/department")
    public final String addDepartment(Model model) {
        Department department = new Department();
        model.addAttribute("department", department);
        model.addAttribute("isNew",true);
        return "department";
    }

    /**
     * Add department page.
     *
     * @return view name
     */
    @PostMapping(value = "/department")
    public final String addDepartment(Department department) {
        //TODO Validation
        departmentService.addDepartment(department);
        return "departments";
    }


}
