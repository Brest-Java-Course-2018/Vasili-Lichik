package com.epam.brest.course.model;

/**
 * POJO Department for model.
 */
public class Department {

    private Integer departmentId;

    private String departmentName;

    private String description;

    public Department() {
    }

    public Department(String departmentName, String description) {
        this.departmentName = departmentName;
        this.description = description;
    }

    public final Integer getDepartmentId() {
        return departmentId;
    }

    public final void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public final String getDepartmentName() {
        return departmentName;
    }

    public final void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public final String getDescription() {
        return description;
    }

    public final void setDescription(String description) {
        this.description = description;
    }

    @Override
    public final String toString() {
        return "Department{"
                +"departmentId=" + departmentId
                +", departmentName='" + departmentName + '\''
                +", description='" + description + '\''
                +'}';
    }
}
