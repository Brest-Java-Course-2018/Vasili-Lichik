package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.model.dto.DepartmentDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final String DEPARTMENT_ID = "departmentId";
    public static final String DEPARTMENT_NAME = "departmentName";
    public static final String DESCRIPTION = "description";
    public static final String AVG_SALARY = "avgSalary";

    @Value("${department.select}")
    private String select;

    @Value("${department.selectById}")
    private String selectById;

    @Value("${department.checkDepartment}")
    private String checkDepartment;

    @Value("${department.insert}")
    private String insert;

    @Value("${department.update}")
    private String update;

    @Value("${department.delete}")
    private String delete;

    @Value("${department.selectDepartmentDTO}")
    private String selectDepartmentDTO;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public DepartmentDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
    /**Return all Department  from DB
     * @return List<Department>
     */
    @Override
    public List<Department> getDepartments() {
        LOGGER.debug("getDepartments()");
        List<Department> departments =
                namedParameterJdbcTemplate.getJdbcOperations().query(select, new DepartmentRowMapper());
        return departments;
    }

//    @Override
//    public Department getDepartmentById(Integer departmentId) {
//        SqlParameterSource namedParameters =
//                new MapSqlParameterSource(DEPARTMENT_ID, departmentId);
//        Department department =
//                namedParameterJdbcTemplate.queryForObject(selectById, namedParameters,
//                        new DepartmentRowMapper());
//        return department;
//    }

    /**Return Department object with appropriate ID from DB
     * getDepartmentById
     * @param departmentId
     * @return department
     */
    @Override
    public Department getDepartmentById(Integer departmentId) {
        LOGGER.debug("getDepartmentById({})",departmentId);
        SqlParameterSource namedParameters =
                new MapSqlParameterSource(DEPARTMENT_ID, departmentId);
        Department department = namedParameterJdbcTemplate.queryForObject(selectById, namedParameters,
                BeanPropertyRowMapper.newInstance(Department.class));
        return department;
    }
    /**
     * Add Department object with appropriate ID to DB
     * @param department
     * @return department
     */
    @Override
    public Department addDepartment(Department department) {
        LOGGER.debug("addDepartment({})",department);
        MapSqlParameterSource namedParameters =
                new MapSqlParameterSource("departmentName", department.getDepartmentName());
        Integer result =
                namedParameterJdbcTemplate.queryForObject(checkDepartment, namedParameters, Integer.class);
        LOGGER.debug("result {}", result);
        if (result == 0) {
            namedParameters = new MapSqlParameterSource();
            namedParameters.addValue("departmentName", department.getDepartmentName());
            namedParameters.addValue("description", department.getDescription());

            KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(insert, namedParameters, generatedKeyHolder);
            department.setDepartmentId(generatedKeyHolder.getKey().intValue());
        } else {
            throw new IllegalArgumentException("Department with the same name already exists in DB.");
        }

        return department;
    }

    @Override
    public void updateDepartment(Department department) {
        LOGGER.debug("updateDepartment({})",department);
        SqlParameterSource namedParameter = new BeanPropertySqlParameterSource(department);
        namedParameterJdbcTemplate.update(update, namedParameter);
    }

    /**Delete Department object with appropriate ID from DB
     * getDepartmentById
     * @param departmentId
     */

    @Override
    public void deleteDepartmentById(Integer departmentId) {
        namedParameterJdbcTemplate.getJdbcOperations().update(delete, departmentId);
    }

    private class DepartmentRowMapper implements RowMapper<Department> {

        @Override
        public Department mapRow(ResultSet resultSet, int i) throws SQLException {
            Department department = new Department();
            department.setDepartmentId(resultSet.getInt(DEPARTMENT_ID));
            department.setDepartmentName(resultSet.getString(DEPARTMENT_NAME));
            department.setDescription(resultSet.getString(DESCRIPTION));
            return department;
        }
    }

    private class DepartmentDTORowMapper implements RowMapper<DepartmentDTO> {

        @Override
        public DepartmentDTO mapRow(ResultSet resultSet, int i) throws SQLException {
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setDepartmentId(resultSet.getInt(DEPARTMENT_ID));
            departmentDTO.setDepartmentName(resultSet.getString(DEPARTMENT_NAME));
            departmentDTO.setAvgSalary(resultSet.getInt(AVG_SALARY));
            return departmentDTO;
        }
    }

    @Override
    public Collection<DepartmentDTO> getDepartmentDTOs(){
        LOGGER.debug("getDepartments()");
        Collection<DepartmentDTO> departments =
                namedParameterJdbcTemplate.getJdbcOperations().query(selectDepartmentDTO, new DepartmentDTORowMapper());
        return departments;
    }


}
