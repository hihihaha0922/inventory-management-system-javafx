package com.jack.inventory.controller;

import com.jack.inventory.dao.ProductDAO;
import com.jack.inventory.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    // Text Fields
    @FXML
    private TextField nameField;

    @FXML
    private TextField categoryField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField quantityField;

    // Buttons
    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button clearButton;

    // TableView
    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product, Integer> idColumn;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TableColumn<Product, String> categoryColumn;

    @FXML
    private TableColumn<Product, Double> priceColumn;

    @FXML
    private TableColumn<Product, Integer> quantityColumn;

    @FXML
    private TextField searchField;

    private final ProductDAO productDAO = new ProductDAO();
    private Product selectedProduct;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        loadProducts();

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchProducts(newValue);
        });

        productTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {

                    if (newValue != null) {
                        showProductDetails(newValue);
                    }

                });
    }

    private void loadProducts() {

        ObservableList<Product> productList =
                FXCollections.observableArrayList(productDAO.getAllProducts());

        productTable.setItems(productList);

    }

    private void searchProducts(String keyword) {

        ObservableList<Product> productList =
                FXCollections.observableArrayList(productDAO.searchProducts(keyword));

        productTable.setItems(productList);
    }

    private void showProductDetails(Product product) {

        selectedProduct = product;

        nameField.setText(product.getName());
        categoryField.setText(product.getCategory());
        priceField.setText(String.valueOf(product.getPrice()));
        quantityField.setText(String.valueOf(product.getQuantity()));

    }

    @FXML
    private void addProduct() {

        try {

            Product product = new Product(
                    nameField.getText(),
                    categoryField.getText(),
                        Double.parseDouble(priceField.getText()),
                    Integer.parseInt(quantityField.getText())
            );

            boolean success = productDAO.addProduct(product);

            if (success) {

                loadProducts();

                nameField.clear();
                categoryField.clear();
                priceField.clear();
                quantityField.clear();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Product added successfully!");
                alert.showAndWait();

            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Failed to add product.");
                alert.showAndWait();

            }

        } catch (NumberFormatException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Price and Quantity must be numbers.");
            alert.showAndWait();

        }

    }

    @FXML
    private void updateProduct() {

        if (selectedProduct == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Please select a product first.");
            alert.showAndWait();
            return;
        }

        try {

            Product product = new Product(
                    nameField.getText(),
                    categoryField.getText(),
                    Double.parseDouble(priceField.getText()),
                    Integer.parseInt(quantityField.getText())
            );

            product.setId(selectedProduct.getId());

            if (productDAO.updateProduct(product)) {

                loadProducts();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Product updated successfully!");
                alert.showAndWait();

            }

        } catch (NumberFormatException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Price and Quantity must be valid numbers.");
            alert.showAndWait();

        }

    }

    @FXML
    private void deleteProduct() {

        if (selectedProduct == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Please select a product first.");
            alert.showAndWait();
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Confirm Delete");
        confirm.setHeaderText(null);
        confirm.setContentText("Are you sure you want to delete this product?");

        if (confirm.showAndWait().get() == ButtonType.OK) {

            if (productDAO.deleteProduct(selectedProduct.getId())) {

                loadProducts();

                nameField.clear();
                categoryField.clear();
                priceField.clear();
                quantityField.clear();

                selectedProduct = null;

                Alert success = new Alert(Alert.AlertType.INFORMATION);
                success.setHeaderText(null);
                success.setContentText("Product deleted successfully!");
                success.showAndWait();

            } else {

                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setHeaderText(null);
                error.setContentText("Failed to delete product.");
                error.showAndWait();
            }
        }
    }

    @FXML
    private void clearFields() {

        nameField.clear();
        categoryField.clear();
        priceField.clear();
        quantityField.clear();

        productTable.getSelectionModel().clearSelection();

        selectedProduct = null;
    }
}