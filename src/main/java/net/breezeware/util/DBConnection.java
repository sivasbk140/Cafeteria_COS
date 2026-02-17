package net.breezeware.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:sqlite:cafeteria.db";
    private static Connection connection = null;

    private DBConnection() {
        // prevent instantiation
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL);
                System.out.println("USING DATABASE FILE: " + URL);
            }
        } catch (SQLException e) {
            System.out.println("ERROR: Could not connect to database.");
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("ERROR: Could not close database connection.");
            e.printStackTrace();
        }
    }
}