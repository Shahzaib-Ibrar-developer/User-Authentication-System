package com.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * CP
 */
public class CP {
public static Connection con;
    public static void createConnection(){
           String jdbcUrl = "jdbc:mysql://localhost:3306/login_db";
        String username = "root";
        String password = "$Shah12345";

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            con = DriverManager.getConnection(jdbcUrl, username, password);

            System.out.println("Connection to MySQL database established.");

            // Perform database operations here

            // Close the connection when done
         
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
