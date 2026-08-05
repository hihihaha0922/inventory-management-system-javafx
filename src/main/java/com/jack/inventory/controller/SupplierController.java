package com.jack.inventory.controller;

import com.jack.inventory.dao.SupplierDAO;
import com.jack.inventory.model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class SupplierController implements Initializable {

    @FXML
    private TextField companyNameField;

    @FXML
    private TextField contactPersonField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField searchField;

    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button clearButton;

    @FXML
    private TableView<Supplier> supplierTable;

    @FXML
    private TableColumn<Supplier, Integer> idColumn;

    @FXML
    private TableColumn<Supplier, String> companyNameColumn;

    @FXML
    private TableColumn<Supplier, String> contactPersonColumn;

    @FXML
    private TableColumn<Supplier, String> phoneColumn;

    @FXML
    private TableColumn<Supplier, String> emailColumn;

    @FXML
    private TableColumn<Supplier, String> addressColumn;

    private final SupplierDAO supplierDAO = new SupplierDAO();

    private Supplier selectedSupplier;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        contactPersonColumn.setCellValueFactory(new PropertyValueFactory<>("contactPerson"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        loadSuppliers();

        supplierTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {

                    if (newValue != null) {
                        showSupplierDetails(newValue);
                    }

                });
    }

    private void loadSuppliers() {

        ObservableList<Supplier> supplierList =
                FXCollections.observableArrayList(
                        supplierDAO.getAllSuppliers());

        supplierTable.setItems(supplierList);

    }

    private void showSupplierDetails(Supplier supplier) {

        selectedSupplier = supplier;

        companyNameField.setText(supplier.getCompanyName());
        contactPersonField.setText(supplier.getContactPerson());
        phoneField.setText(supplier.getPhone());
        emailField.setText(supplier.getEmail());
        addressField.setText(supplier.getAddress());

    }

    @FXML
    private void addSupplier() {

        Supplier supplier = new Supplier(
                companyNameField.getText(),
                contactPersonField.getText(),
                phoneField.getText(),
                emailField.getText(),
                addressField.getText()
        );

        if (supplierDAO.addSupplier(supplier)) {

            loadSuppliers();
            clearFields();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Supplier added successfully!");
            alert.showAndWait();

        }

    }

    @FXML
    private void updateSupplier() {

        if (selectedSupplier == null)
            return;

        Supplier supplier = new Supplier(
                companyNameField.getText(),
                contactPersonField.getText(),
                phoneField.getText(),
                emailField.getText(),
                addressField.getText()
        );

        supplier.setId(selectedSupplier.getId());

        if (supplierDAO.updateSupplier(supplier)) {

            loadSuppliers();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Supplier updated successfully!");
            alert.showAndWait();

        }

    }

    @FXML
    private void deleteSupplier() {

        if (selectedSupplier == null)
            return;

        if (supplierDAO.deleteSupplier(selectedSupplier.getId())) {

            loadSuppliers();
            clearFields();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Supplier deleted successfully!");
            alert.showAndWait();

        }

    }

    @FXML
    private void clearFields() {

        companyNameField.clear();
        contactPersonField.clear();
        phoneField.clear();
        emailField.clear();
        addressField.clear();

        selectedSupplier = null;

    }

}