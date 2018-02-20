package com.epam.brest.course;

import org.junit.Assert;
import org.junit.Ignore;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBUtilsTest {

    @org.junit.Test
    public void getConnection() throws SQLException, ClassNotFoundException {
        DBUtils dbUtils = new DBUtils();
        Assert.assertNotNull(dbUtils.getConnection());
    }
    @Ignore
    @org.junit.Test
    public void addUser() throws SQLException, ClassNotFoundException {
        DBUtils dbUtils = new DBUtils();
        Connection connection = dbUtils.getConnection();
        dbUtils.createTable(connection);
        String newUser = "INSERT INTO app_user (login,password,description) VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(newUser);
        preparedStatement.setString(1,"login");
        preparedStatement.setString(2,"pass");
        preparedStatement.setString(3,"description");
        int x = preparedStatement.executeUpdate();
        Assert.assertEquals(1L,x);
    }
}