package com.jack.inventory.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.jack.inventory.dao.UserDAO;
import com.jack.inventory.model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private void handleLogin() {

        UserDAO userDAO = new UserDAO();

        User user = userDAO.login(
                usernameField.getText(),
                passwordField.getText()
        );

        if (user != null) {

            try {

                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/fxml/dashboard.fxml"));

                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setTitle("Smart Inventory System");
                stage.setScene(new Scene(root));
                stage.show();

                ((Stage) loginButton.getScene().getWindow()).close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Login Failed");
            alert.setContentText(
                    "Invalid username or password."
            );

            alert.showAndWait();

        }

    }
}