package net.breezeware;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLiteConnect {

    private static final String DB_URL =
            "jdbc:sqlite:C:/Users/preet/Cafeteria_COS/cafeteria.db";

    public static void initDatabase() {

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            // SQLite needs this every connection
            stmt.execute("PRAGMA foreign_keys = ON;");

            // Load Create.sql from resources
            InputStream is = SQLiteConnect.class
                    .getClassLoader()
                    .getResourceAsStream("Create.sql");

            if (is == null) {
                throw new RuntimeException("Create.sql not found in resources!");
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sql = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty() ) {
                    continue;
                }

                sql.append(line);

                // Execute one statement at a time
                if (line.endsWith(";")) {
                    stmt.execute(sql.toString());
                    sql.setLength(0);
                }
            }

            System.out.println(" Database schema created successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
