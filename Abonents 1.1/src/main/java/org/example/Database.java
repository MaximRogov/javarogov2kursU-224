package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private final static String userName = "root";
    private final static String password = "30102023";
    private final static String connectionUrl = "jdbc:mysql://localhost:3306/new_schema";

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(connectionUrl, userName, password);
        }
        return connection;
    }
}
