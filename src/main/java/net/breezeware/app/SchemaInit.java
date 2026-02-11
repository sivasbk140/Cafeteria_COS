package net.breezeware.app;

import net.breezeware.util.DBConnection;

import java.sql.Connection;
import java.sql.Statement;

public class SchemaInit {

    public static void init() {
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement()) {

            st.execute("""
                CREATE TABLE IF NOT EXISTS Users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    password TEXT NOT NULL,
                    role TEXT NOT NULL,
                    email TEXT NOT NULL UNIQUE,
                    created_on TIMESTAMP,
                    updated_on TIMESTAMP
                )
            """);


            st.execute("""
                CREATE TABLE IF NOT EXISTS Food_Item (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    price REAL NOT NULL,
                    quantity INTEGER,
                    category TEXT NOT NULL,
                    description TEXT NOT NULL,
                    created_on TIMESTAMP,
                    updated_on TIMESTAMP
                )
            """);

            st.execute("""
                CREATE TABLE IF NOT EXISTS Food_Menu (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    category TEXT NOT NULL,
                    created_on TIMESTAMP,
                    updated_on TIMESTAMP
                )
            """);

            st.execute("""
                CREATE TABLE IF NOT EXISTS Food_Menu_Items_Map (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    menu_id INTEGER NOT NULL,
                    food_item_id INTEGER NOT NULL,
                    is_available INTEGER DEFAULT 1,
                    created_on TIMESTAMP,
                    updated_on TIMESTAMP
                )
            """);


            st.execute("""
                CREATE TABLE IF NOT EXISTS Order_Table (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    user_id INTEGER NOT NULL,
                    status TEXT NOT NULL,
                    created_on TIMESTAMP,
                    updated_on TIMESTAMP
                )
            """);

            st.execute("""
                CREATE TABLE IF NOT EXISTS Order_Items (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    order_id INTEGER NOT NULL,
                    food_menu_item_id INTEGER NOT NULL,
                    price REAL NOT NULL,
                    quantity INTEGER NOT NULL,
                    created_on TIMESTAMP,
                    updated_on TIMESTAMP
                )
            """);

            st.execute("""
                CREATE TABLE IF NOT EXISTS Delivery_Details (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    email TEXT NOT NULL,
                    phone_number TEXT NOT NULL,
                    location TEXT NOT NULL,
                    user_id INTEGER NOT NULL,
                    created_on TIMESTAMP,
                    updated_on TIMESTAMP
                )
            """);

            st.execute("""
                CREATE TABLE IF NOT EXISTS Order_Delivery_Map (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    order_id INTEGER NOT NULL,
                    delivery_id INTEGER NOT NULL,
                    created_on TIMESTAMP,
                    updated_on TIMESTAMP
                )
            """);
            st.execute("""
                            INSERT INTO Food_Menu (name, description, price) VALUES 
                          ('Cheese Burger', 'Delicious burger with cheese', 120),
('Veg Pizza', 'Pizza topped with fresh vegetables', 250),
('Grilled Sandwich', 'Toasted sandwich with veggies and cheese', 100),
('Pasta Alfredo', 'Creamy white sauce pasta', 180),
('Chicken Wrap', 'Spicy grilled chicken wrap', 150);

""");

            System.out.println("Schema initialization completed");

        } catch (Exception e) {
            throw new RuntimeException(" Schema init failed", e);
        }
    }
}
