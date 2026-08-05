package com.jack.inventory.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {

    @FXML
    private Button dashboardButton;

    @FXML
    private Button productsButton;

    @FXML
    private Button categoriesButton;

    @FXML
    private Button suppliersButton;

    @FXML
    private Button reportsButton;

    @FXML
    private Button logoutButton;

    @FXML
    private void openProducts(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/product.fxml"));

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Product Management");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void openCategories(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/category.fxml"));

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Category Management");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void logout(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/login.fxml"));

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
            stage.show();

            ((Stage) logoutButton.getScene().getWindow()).close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void openSupplier() {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/supplier.fxml"));

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Supplier Management");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}