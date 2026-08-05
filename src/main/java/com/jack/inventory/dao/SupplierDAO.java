package com.jack.inventory.dao;

import com.jack.inventory.database.DatabaseConnection;
import com.jack.inventory.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {

    private final Connection connection;

    public SupplierDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public List<Supplier> getAllSuppliers() {

        List<Supplier> supplierList = new ArrayList<>();

        String sql = "SELECT * FROM suppliers";

        try {

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                supplierList.add(new Supplier(
                        rs.getInt("id"),
                        rs.getString("company_name"),
                        rs.getString("contact_person"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address")
                ));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return supplierList;
    }

    public boolean addSupplier(Supplier supplier) {

        String sql =
                "INSERT INTO suppliers(company_name, contact_person, phone, email, address) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, supplier.getCompanyName());
            statement.setString(2, supplier.getContactPerson());
            statement.setString(3, supplier.getPhone());
            statement.setString(4, supplier.getEmail());
            statement.setString(5, supplier.getAddress());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateSupplier(Supplier supplier) {

        String sql =
                "UPDATE suppliers SET company_name=?, contact_person=?, phone=?, email=?, address=? WHERE id=?";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, supplier.getCompanyName());
            statement.setString(2, supplier.getContactPerson());
            statement.setString(3, supplier.getPhone());
            statement.setString(4, supplier.getEmail());
            statement.setString(5, supplier.getAddress());
            statement.setInt(6, supplier.getId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteSupplier(int id) {

        String sql = "DELETE FROM suppliers WHERE id=?";

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}