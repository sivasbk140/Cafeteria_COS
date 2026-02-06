package net.breezeware.order.dao;

import net.breezeware.order.entity.Order;
import net.breezeware.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class OrderDao {

    public void create(Order order) throws SQLException {

        String sql = """ 
            INSERT INTO Order ( status, user_id, created_on)
            VALUES (?, ?, ?, , TIMESTAMP)""" ;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, order.getStatus().toString());
            ps.setInt(2, order.getUser_id());

            ps.executeUpdate();
        }
    }
    }

