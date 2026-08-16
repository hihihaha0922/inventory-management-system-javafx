# Smart Inventory System

A desktop-based inventory management system developed using **Java, JavaFX, MySQL, and Maven**. The system provides a graphical interface for managing products, categories, suppliers, and user authentication.

## Features

* 🔐 **User Authentication**

  * Login system connected to MySQL
  * User credential verification

* 📦 **Product Management**

  * Add products
  * View products
  * Update products
  * Delete products
  * Search products
  * Track product quantity

* 🗂️ **Category Management**

  * Add categories
  * View categories
  * Update categories
  * Delete categories

* 🏢 **Supplier Management**

  * Add suppliers
  * View suppliers
  * Update suppliers
  * Delete suppliers

* 🖥️ **Dashboard**

  * Central navigation interface for the different management modules

* ⚠️ **Input Validation**

  * Validation for required fields
  * Numeric validation for price and quantity
  * Error and success messages

## Technologies Used

| Technology        | Purpose                                           |
| ----------------- | ------------------------------------------------- |
| **Java**          | Application logic and object-oriented programming |
| **JavaFX**        | Desktop graphical user interface                  |
| **FXML**          | UI layout and interface structure                 |
| **Scene Builder** | Visual design of JavaFX interfaces                |
| **MySQL**         | Database and persistent data storage              |
| **JDBC**          | Connection between Java and MySQL                 |
| **Maven**         | Dependency and project management                 |
| **Git**           | Version control                                   |
| **GitHub**        | Source code hosting                               |

## Project Architecture

The project follows a modular structure separating the user interface, application logic, data models, and database operations.

```text
src/
└── main/
    ├── java/
    │   └── com.jack.inventory/
    │       ├── controller/
    │       │   ├── LoginController.java
    │       │   ├── DashboardController.java
    │       │   ├── ProductController.java
    │       │   ├── CategoryController.java
    │       │   └── SupplierController.java
    │       │
    │       ├── dao/
    │       │   ├── ProductDAO.java
    │       │   ├── CategoryDAO.java
    │       │   └── SupplierDAO.java
    │       │
    │       ├── database/
    │       │   └── DatabaseConnection.java
    │       │
    │       ├── model/
    │       │   ├── Product.java
    │       │   ├── Category.java
    │       │   └── Supplier.java
    │       │
    │       └── Main.java
    │
    └── resources/
        └── fxml/
            ├── login.fxml
            ├── dashboard.fxml
            ├── product.fxml
            ├── category.fxml
            └── supplier.fxml
```

## How the System Works

The application follows this general flow:

```text
User
  ↓
JavaFX / FXML Interface
  ↓
Controller
  ↓
Model
  ↓
DAO
  ↓
JDBC
  ↓
MySQL Database
```

### JavaFX + FXML

**JavaFX** provides the desktop UI components such as:

* `Stage`
* `Scene`
* `Button`
* `TextField`
* `TableView`
* `TableColumn`

**FXML** defines the layout of the interface, while **Scene Builder** is used to visually design the FXML files.

### Controller

Controllers handle user interactions.

For example:

```text
User clicks Add
      ↓
ProductController
      ↓
Creates Product object
      ↓
ProductDAO
      ↓
MySQL
```

### Model

Model classes represent the application's data.

Examples:

```text
Product
Category
Supplier
User
```

### DAO

DAO stands for **Data Access Object**.

The DAO classes handle database operations such as:

```text
SELECT
INSERT
UPDATE
DELETE
```

For example:

```java
productDAO.addProduct(product);
```

### JDBC

JDBC (**Java Database Connectivity**) allows the Java application to communicate with MySQL.

```text
Java Application
       ↓
      JDBC
       ↓
      MySQL
```

## Database

The system uses MySQL to store application data.

Current database entities include:

```text
users
products
categories
suppliers
```

## Example Product Data

| ID | Name     | Category    |  Price | Quantity |
| -: | -------- | ----------- | -----: | -------: |
|  1 | Mouse    | Electronics |  59.90 |       30 |
|  2 | Keyboard | Electronics | 119.90 |       20 |
|  3 | Monitor  | Electronics | 799.90 |       10 |

## Project Status

### Completed

* [x] MySQL database connection
* [x] User authentication
* [x] Dashboard
* [x] Product CRUD
* [x] Category CRUD
* [x] Supplier CRUD
* [x] Product search
* [x] Input validation
* [x] JavaFX/FXML interface

### Planned

* [ ] Purchase Management
* [ ] Sales Management
* [ ] Automated stock updates
* [ ] Inventory reports
* [ ] Dashboard statistics
* [ ] UI improvements

## Running the Project

### Requirements

* Java 21
* Maven
* MySQL Server
* MySQL Workbench
* IntelliJ IDEA or another Java IDE

### Database Setup

1. Install and start MySQL Server.
2. Create the required database.
3. Create the required tables.
4. Update the database connection settings in:

```text
DatabaseConnection.java
```

5. Make sure the MySQL server is running.

### Run the Application

Using Maven:

```bash
mvn clean javafx:run
```

Or run `Main.java` directly from your IDE.

## Learning Objectives

This project was developed to gain practical experience with:

* Java object-oriented programming
* JavaFX desktop application development
* FXML and Scene Builder
* MVC-style application structure
* DAO pattern
* JDBC database connectivity
* MySQL database design
* CRUD operations
* Input validation
* Git and GitHub version control

## Author

**Jack Kee Joo Jing**

Information Technology (Game Technology) Student
Universiti Teknikal Malaysia Melaka (UTeM)
