package com.jack.inventory.dao;

import com.jack.inventory.database.DatabaseConnection;
import com.jack.inventory.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    private Connection connection;

    public CategoryDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public List<Category> getAllCategories() {

        List<Category> categoryList = new ArrayList<>();

        String sql = "SELECT * FROM categories";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                Category category = new Category(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                );

                categoryList.add(category);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return categoryList;
    }

    public boolean addCategory(Category category) {

        String sql = "INSERT INTO categories(name, description) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateCategory(Category category) {

        String sql = "UPDATE categories SET name=?, description=? WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.setInt(3, category.getId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteCategory(int id) {

        String sql = "DELETE FROM categories WHERE id=?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}