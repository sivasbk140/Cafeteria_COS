package net.breezeware.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {


    private static final String DB_URL =
            "jdbc:sqlite:C:/Users/preet/Cafeteria_COS/Backend/cafeteria.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(" SQLite JDBC Driver not found", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        System.out.println(" USING DATABASE FILE: " + DB_URL);
        return DriverManager.getConnection(DB_URL);
    }
}
