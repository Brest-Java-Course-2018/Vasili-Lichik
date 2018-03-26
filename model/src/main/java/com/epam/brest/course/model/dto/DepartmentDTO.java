package com.epam.brest.course.model.dto;

public class DepartmentDTO {
    private Integer DepartmentId;
    private String DepartmentName;
    private Integer AvgSalary;

    public DepartmentDTO(Integer departmentId, String departmentName, Integer avgSalary) {
        DepartmentId = departmentId;
        DepartmentName = departmentName;
        AvgSalary = avgSalary;
    }

    public DepartmentDTO() {}

    public Integer getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        DepartmentId = departmentId;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public Integer getAvgSalary() {
        return AvgSalary;
    }

    public void setAvgSalary(Integer avgSalary) {
        AvgSalary = avgSalary;
    }
}
