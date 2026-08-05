package com.jack.inventory.controller;

import com.jack.inventory.dao.CategoryDAO;
import com.jack.inventory.model.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {

    @FXML
    private TextField nameField;

    @FXML
    private TextField descriptionField;

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
    private TableView<Category> categoryTable;

    @FXML
    private TableColumn<Category, Integer> idColumn;

    @FXML
    private TableColumn<Category, String> nameColumn;

    @FXML
    private TableColumn<Category, String> descriptionColumn;

    private final CategoryDAO categoryDAO = new CategoryDAO();

    private Category selectedCategory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        loadCategories();

        categoryTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {

                    if (newValue != null) {
                        showCategoryDetails(newValue);
                    }

                });

    }

    private void loadCategories() {

        ObservableList<Category> categoryList =
                FXCollections.observableArrayList(categoryDAO.getAllCategories());

        categoryTable.setItems(categoryList);

    }

    private void showCategoryDetails(Category category) {

        selectedCategory = category;

        nameField.setText(category.getName());
        descriptionField.setText(category.getDescription());

    }

    @FXML
    private void addCategory() {

        if (nameField.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Category name cannot be empty.");
            alert.showAndWait();
            return;
        }

        Category category = new Category(
                nameField.getText(),
                descriptionField.getText()
        );

        if (categoryDAO.addCategory(category)) {

            loadCategories();

            nameField.clear();
            descriptionField.clear();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Category added successfully!");
            alert.showAndWait();

        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Failed to add category.");
            alert.showAndWait();

        }

    }

    @FXML
    private void updateCategory() {

        if (selectedCategory == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Please select a category first.");
            alert.showAndWait();
            return;
        }

        Category category = new Category(
                nameField.getText(),
                descriptionField.getText()
        );

        category.setId(selectedCategory.getId());

        if (categoryDAO.updateCategory(category)) {

            loadCategories();
            clearFields();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Category updated successfully!");
            alert.showAndWait();

        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Failed to update category.");
            alert.showAndWait();

        }
    }

    @FXML
    private void deleteCategory() {

        if (selectedCategory == null) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Please select a category first.");
            alert.showAndWait();
            return;
        }

        if (categoryDAO.deleteCategory(selectedCategory.getId())) {

            loadCategories();
            clearFields();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Category deleted successfully!");
            alert.showAndWait();

        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Failed to delete category.");
            alert.showAndWait();

        }

    }

    @FXML
    private void clearFields() {

        nameField.clear();
        descriptionField.clear();

        categoryTable.getSelectionModel().clearSelection();

        selectedCategory = null;

    }
}