package net.breezeware.user.dao;

import net.breezeware.user.dto.UserDTO;
import net.breezeware.user.entity.Role;
import net.breezeware.user.entity.User;
import net.breezeware.util.DBConnection;

import java.sql.*;

public class UserDao {

    // Register New User
    public int registerUser(String name, String email, String password, Role role) {
        String sql = "INSERT INTO Users (name, email, password, role, created_on, updated_on) " +
                "VALUES (?, ?, ?, ?, datetime('now'), datetime('now'))";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setString(4, role.name());

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to register user.");
            e.printStackTrace();
        }

        return -1;
    }

    //  Login (authenticate user)
    public UserDTO login(String email, String password, Role expectedRole) {
        String sql = "SELECT id, name, email, role FROM Users WHERE email = ? AND password = ? AND role = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            pstmt.setString(2, password);
            pstmt.setString(3, expectedRole.name());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new UserDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        Role.fromString(rs.getString("role"))
                );
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Login failed.");
            e.printStackTrace();
        }

        return null;
    }

    //  Get User By Email
    public User getUserByEmail(String email) {
        String sql = "SELECT id, name, email, password, role, created_on, updated_on FROM Users WHERE email = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("password"),
                        Role.fromString(rs.getString("role")),
                        rs.getString("email"),
                        rs.getString("created_on"),
                        rs.getString("updated_on")
                );
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to fetch user by email.");
            e.printStackTrace();
        }

        return null;
    }

    //  Get User By ID
    public User getUserById(int id) {
        String sql = "SELECT id, name, email, password, role, created_on, updated_on FROM Users WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("password"),
                        Role.fromString(rs.getString("role")),
                        rs.getString("email"),
                        rs.getString("created_on"),
                        rs.getString("updated_on")
                );
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to fetch user by ID.");
            e.printStackTrace();
        }

        return null;
    }

    // Update User
    public boolean updateUser(int id, String name, String email, String password) {
        String sql = "UPDATE Users SET name = ?, email = ?, password = ?, updated_on = datetime('now') WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setInt(4, id);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to update user.");
            e.printStackTrace();
        }

        return false;
    }

    //  Delete User
    public boolean deleteUser(int id) {
        String sql = "DELETE FROM Users WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to delete user.");
            e.printStackTrace();
        }

        return false;
    }

    //  Check if Email Exists
    public boolean emailExists(String email) {
        String sql = "SELECT COUNT(*) FROM Users WHERE email = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Failed to check email existence.");
            e.printStackTrace();
        }

        return false;
    }
}