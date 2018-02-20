package com.epam.brest.course;

import java.sql.*;

public class DBUtils {
    public Connection getConnection () throws ClassNotFoundException, SQLException {
        String databaseURL =
                "jdbc:h2:mem:test_db:MODE=MYSQL:DB_CLOSE_DELAY=-1";
        Class.forName("org.h2.Driver");
        Connection connection =
                DriverManager.getConnection(databaseURL,"sa","");
        return connection;

    }

    public void createTable(Connection connection) throws SQLException {
        System.out.println("create table");
        String createTable = "CREATE TABLE app_user (" +
                "user_id INT NOT NULL AUTO_INCREMENT," +
                "login VARCHAR(255) NOT NULL," +
                "password VARCHAR(255) NOT NULL," +
                "description VARCHAR(255) NULL," +
                "PRIMARY KEY (user_id)" +
                ")";
        Statement statement = connection.createStatement();
        statement.executeUpdate(createTable);


    }
    public void addUser(Connection connection, String login, String pass, String description) throws SQLException {

        String newUser = "INSERT INTO app_user (login,password,description) VALUES (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(newUser);
        preparedStatement.setString(1,login);
        preparedStatement.setString(2,pass);
        preparedStatement.setString(3,description);
        int x = preparedStatement.executeUpdate();
        System.out.println ("add "+x+"strings");

    }

    public void getUsers (Connection connection) throws SQLException {
        String getRecords ="SELECT user_id, login,description FROM app_user";
        Statement statement =connection.createStatement();
        ResultSet resultSet = statement.executeQuery(getRecords);
        while (resultSet.next()){
            System.out.println(String.format("User: %s, %s, %s",
                    resultSet.getInt("user_id"),
                    resultSet.getString("login"),
                    resultSet.getString("description")
                    ));
        }
    }

    public void deleteUser (Connection connection, int userId) throws SQLException {
        String delRecord ="DELETE FROM app_user WHERE user_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(delRecord);
        preparedStatement.setInt(1,userId);
        int x =preparedStatement.executeUpdate();
        System.out.println (String.format("deleted %s lines",x));

    }


}
