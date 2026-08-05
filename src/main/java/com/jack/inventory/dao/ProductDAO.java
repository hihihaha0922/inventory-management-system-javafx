package com.jack.inventory.dao;

import com.jack.inventory.database.DatabaseConnection;
import com.jack.inventory.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private Connection connection;

    public ProductDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public List<Product> getAllProducts() {

        List<Product> productList = new ArrayList<>();

        String sql = "SELECT * FROM products";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                );

                productList.add(product);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return productList;

    }
    public boolean addProduct(Product product) {

        String sql = "INSERT INTO products(name, category, price, quantity) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, product.getName());
            statement.setString(2, product.getCategory());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getQuantity());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateProduct(Product product) {

        String sql = "UPDATE products SET name = ?, category = ?, price = ?, quantity = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, product.getName());
            statement.setString(2, product.getCategory());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getQuantity());
            statement.setInt(5, product.getId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteProduct(int id) {

        String sql = "DELETE FROM products WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Product> searchProducts(String keyword) {

        List<Product> productList = new ArrayList<>();

        String sql = "SELECT * FROM products WHERE name LIKE ? OR category LIKE ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                );

                productList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }
}