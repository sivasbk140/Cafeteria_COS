package net.breezeware.user.dao;

import net.breezeware.user.entity.User;
import net.breezeware.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {

    public boolean existsByEmail(String email) throws Exception {

        String sql = "SELECT 1 FROM Users WHERE email = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            return ps.executeQuery().next();
        }
    }

    public void create(User user) throws Exception {

        String sql = """
                INSERT INTO Users (name, email, password, role)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole().name());

            ps.executeUpdate();
        }
    }
}
