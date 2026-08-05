package com.jack.inventory.database;

import java.sql.Connection;

public class DatabaseTest {

    public static void main(String[] args) {

        Connection connection = DatabaseConnection.getConnection();

        if (connection != null) {

            System.out.println("✅ Database Connected Successfully!");

        } else {

            System.out.println("❌ Database Connection Failed!");

        }

    }
}