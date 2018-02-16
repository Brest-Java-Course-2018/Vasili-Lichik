package com.epam.brest.course;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException, ClassNotFoundException {
        System.out.println( "Hello World!" );
        DBUtils dbUtils = new DBUtils();
        Connection connection = dbUtils.getConnection();
        dbUtils.createTable(connection);
        dbUtils.addUser(connection,"Vasili","pass","admin");
        dbUtils.addUser(connection,"Vanya","pass","admin");
        dbUtils.addUser(connection,"Pasha","pass","admin");
        dbUtils.getUsers(connection);

    }
}
