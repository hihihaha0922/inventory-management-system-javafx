package com.jack.inventory.dao;

import com.jack.inventory.database.DatabaseConnection;
import com.jack.inventory.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public User login(String username, String password) {

        String sql = """
                SELECT *
                FROM users
                WHERE username = ?
                AND password = ?
                """;

        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                User user = new User();

                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFullName(rs.getString("full_name"));
                user.setRole(rs.getString("role"));

                return user;
            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;
    }
}