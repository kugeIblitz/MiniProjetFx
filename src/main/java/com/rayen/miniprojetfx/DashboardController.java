package com.rayen.miniprojetfx;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import connexion.connexion;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.scene.chart.XYChart;
import javafx.util.converter.DoubleStringConverter;






public class DashboardController {

    public Button inventory_clearBtn;
    @FXML
    private TextArea Description;
    @FXML
    private Button menu_GoGreenBtn;

    @FXML
    private Button dashboard_btn;
    @FXML
    private Button inventory_btn;
    @FXML
    private Button menu_btn;
    @FXML
    private Button customers_btn;
    @FXML
    private AnchorPane inventory_form;

    @FXML
    private AnchorPane menu_form;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private AnchorPane customers_form;

    @FXML
    private ScrollPane menu_scrollPane;

    @FXML
    private GridPane menu_gridPane;

    @FXML
    private TableView<Product> menu_tableView;

    @FXML
    private TableColumn menu_col_productName;

    @FXML
    private TableColumn menu_col_quantity;

    @FXML
    private VBox card;


    @FXML
    private AreaChart<String, Number> dashboard_greenChart;
    private CategoryAxis xAxis = new CategoryAxis();
    private NumberAxis yAxis = new NumberAxis();

    private XYChart.Series<String, Double> series = new XYChart.Series<>();
    private List<Double> envSaveData = new ArrayList<>();


    @FXML
    private TableView<Product> tableView;
    @FXML
    private TableColumn<Product, String> colGreenProduct;

    @FXML
    private AnchorPane menu_anchorPane;

    @FXML
    private TextField inventory_productName;

    @FXML
    private TextField inventory_price;

    @FXML
    private TextField imageUrl;

    @FXML
    private Button inventory_addBtn;

    @FXML
    private TableView<Product> inventory_tableView;

    @FXML
    private TableColumn<Product, Integer> inventory_col_productID;

    @FXML
    private TableColumn<Product, String> inventory_col_productName;

    @FXML
    private TableColumn<Product, Double> inventory_col_productPrice;

    @FXML
    private TableColumn<Product, String> inventory_col_productImage;

    @FXML
    private TableColumn<Product, String> inventory_col_productDesc;

    @FXML
    private Button inventory_deleteBtn;

    // ObservableList to store the data for the menu_tableView
    private ObservableList<Product> menuData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        inventory_tableView.setEditable(true);
        inventory_col_productID.setCellValueFactory(new PropertyValueFactory<>("id"));
        inventory_col_productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventory_col_productPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        inventory_col_productImage.setCellValueFactory(new PropertyValueFactory<>("imageUrl"));
        inventory_col_productDesc.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Set cell factories to enable editing
        inventory_col_productName.setCellFactory(TextFieldTableCell.forTableColumn());
        inventory_col_productPrice.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        inventory_col_productImage.setCellFactory(TextFieldTableCell.forTableColumn());
        inventory_col_productDesc.setCellFactory(TextFieldTableCell.forTableColumn());

        // Set event handlers for editing
        inventory_col_productName.setOnEditCommit(event -> {
            Product product = event.getRowValue();
            product.setName(event.getNewValue());
            updateProductAttribute(product, "nomP", event.getNewValue());
        });
        inventory_col_productPrice.setOnEditCommit(event -> {
            Product product = event.getRowValue();
            product.setPrice(event.getNewValue());
            updateProductAttribute(product, "price", String.valueOf(event.getNewValue()));
        });
        inventory_col_productImage.setOnEditCommit(event -> {
            Product product = event.getRowValue();
            product.setImage(event.getNewValue());
            updateProductAttribute(product, "image", event.getNewValue());
        });
        inventory_col_productDesc.setOnEditCommit(event -> {
            Product product = event.getRowValue();
            product.setDescription(event.getNewValue());
            updateProductAttribute(product, "description", event.getNewValue());
        });

        // Allow editing only on double-click
        inventory_tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && inventory_tableView.getSelectionModel().getSelectedItem() != null) {
                inventory_tableView.edit(inventory_tableView.getSelectionModel().getSelectedIndex(), inventory_col_productName);
            }
        });

        inventory_clearBtn.setOnAction(event -> {
            inventory_price.clear();
            imageUrl.clear();
            Description.clear();
            inventory_productName.clear();
        });





        menu_col_productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        menu_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        menu_GoGreenBtn.setOnAction(this::openGoGreenWindow);
        menu_form.setVisible(true);
        inventory_addBtn.setOnAction(event -> addProductToDatabase());
        menu_tableView.setItems(menuData);

        inventory_col_productID.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        inventory_col_productName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        inventory_col_productPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        inventory_col_productImage.setCellValueFactory(cellData -> cellData.getValue().imageProperty());
        inventory_col_productDesc.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        inventory_tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
                inventory_deleteBtn.setDisable(newValue == null));

        // Fetch data and populate the table view
        fetchData();

        loadCardProductData();
    }



    private void updateProductAttribute(Product product, String attribute, String newValue) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/green", "root", "")) {
            String updateQuery = "UPDATE userpro SET " + attribute + " = ? WHERE idP = ?";
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, newValue);
            statement.setInt(2, product.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void deleteSelectedProduct() {
        Product selectedProduct = inventory_tableView.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            int productId = selectedProduct.getId();

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/green", "root", "")) {
                String deleteQuery = "DELETE FROM userpro WHERE idP = ?";
                PreparedStatement statement = connection.prepareStatement(deleteQuery);
                statement.setInt(1, productId);
                statement.executeUpdate();


                // Remove the selected product from the table view
                inventory_tableView.getItems().remove(selectedProduct);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    private void fetchData() {
        // Assuming you have a database connection
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/green", "root", "")) {
            String selectQuery = "SELECT idP, nomP, description, price, image FROM userpro";
            ResultSet resultSet = connection.createStatement().executeQuery(selectQuery);

            while (resultSet.next()) {
                int id = resultSet.getInt("idP");
                String name = resultSet.getString("nomP");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                String image = resultSet.getString("image");

                Product product = new Product(id, name, description, price, image);
                inventory_tableView.getItems().add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void addProductToDatabase() {
        String productName = inventory_productName.getText();
        double price = Double.parseDouble(inventory_price.getText());
        String imageLink = imageUrl.getText();
        String description = Description.getText();

        // Assuming you have a database connection
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/green", "root", "")) {
            String insertQuery = "INSERT INTO userpro (nomp, price, image, description) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, productName);
            statement.setDouble(2, price);
            statement.setString(3, imageLink);
            statement.setString(4, description);
            statement.executeUpdate();

            System.out.println("Product added to the database successfully.");

            // Clear input fields after successful insertion
            inventory_productName.clear();
            inventory_price.clear();
            imageUrl.clear();
            Description.clear();

            loadCardProductData();

            // Refresh the table view with updated data
               fetchData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void handleGoGreenBtn() {
        // Clear existing data in tableView
        tableView.getItems().clear();

        // Get the data from menu_tableView and add it to tableView
        for (Product product : menu_tableView.getItems()) {
            tableView.getItems().add(new Product(product.getName()));
        }

        // Set the cell value factory for colGreenProduct
        colGreenProduct.setCellValueFactory(new PropertyValueFactory<>("name"));
    }




    @FXML
    private void openGoGreenWindow(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("goGreen.fxml"));
            Parent root = loader.load();
            GoGreenController goGreenController = loader.getController();

            ObservableList<Product> products = menu_tableView.getItems();
            ObservableList<Product> greenProducts = FXCollections.observableArrayList();

            for (Product product : products) {
                greenProducts.add(new Product(product.getName(), product.getQuantity()));
            }

            // Retrieve the description and envSave values for each product name
            for (Product greenProduct : greenProducts) {
                String name = greenProduct.getName();
                String selectQuery = "SELECT description, envSave FROM greenpro WHERE nomG = ?";

                // Assuming you have a database connection
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/green", "root", "");

                PreparedStatement statement = connection.prepareStatement(selectQuery);
                statement.setString(1, name);

                // Execute the select query and retrieve the values
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String description = resultSet.getString("description");
                    int envSave = resultSet.getInt("envSave");
                    greenProduct.setDescription(description);
                    greenProduct.setYouSaved(envSave + " Tree");
                }

                // Close the statement and result set
                resultSet.close();
                statement.close();
            }

            goGreenController.setGreenProducts(greenProducts);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Go Green");
            stage.show();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }








    private void loadCardProductData() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/green", "root", "");
            if (connection != null) {
                System.out.println("Database Connected");
            }

            String query = "SELECT nomP, image FROM userpro";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            int maxCardsPerRow = 2; // Number of cards to display in each row
            int row = 0;
            int col = 0;

            // Set the spacing between items
            int horizontalSpacing = 10;
            int verticalSpacing = 10;

            while (resultSet.next()) {
                String nomP = resultSet.getString("nomP");
                String imageLink = resultSet.getString("image");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/rayen/miniprojetfx/cardProduct.fxml"));
                AnchorPane cardProductPane = loader.load();

                // Access the controller of the loaded cardProduct.fxml
                CardProductController cardProductController = loader.getController();
                cardProductController.setProductData(nomP, imageLink); // Set the product data and image link

                // Set row and column constraints for the cardProductPane
                GridPane.setRowIndex(cardProductPane, row);
                GridPane.setColumnIndex(cardProductPane, col);

                // Set the margin around the cardProductPane
                Insets margin = new Insets(verticalSpacing / 2, horizontalSpacing / 2,
                        verticalSpacing / 2, horizontalSpacing / 2);
                GridPane.setMargin(cardProductPane, margin);

                // Add the cardProductPane to the GridPane
                menu_gridPane.getChildren().add(cardProductPane);

                Button addButton = (Button) cardProductPane.lookup("#prod_addBtn");
                addButton.setOnAction(event -> addSelectedProductToMenu(nomP));

                col++;
                if (col >= maxCardsPerRow) {
                    // Move to the next row when reaching the maximum number of cards per row
                    col = 0;
                    row++;
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (resultSet != null)
                    resultSet.close();
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    private void addSelectedProductToMenu(String productName) {
        // Check if the product is already in the menu
        for (Product product : menuData) {
            if (product.getName().equals(productName)) {
                // Increment the quantity if the product already exists
                product.setQuantity(product.getQuantity() + 1);
                return; // Exit the method
            }
        }

        // If the product is not in the menu, add it with quantity 1
        Product product = new Product(productName, 1);
        menuData.add(product);
    }



    @FXML
    private void handleRemoveButtonAction(ActionEvent event) {
        // Get the selected products from the TableView
        ObservableList<Product> selectedProducts = menu_tableView.getItems();

        // Remove the selected products from the TableView
        menu_tableView.getItems().removeAll(selectedProducts);
    }


//------------------------------------------Switch Form-------------------------------------------------
        public void switchForm(ActionEvent event) {

        if (event.getSource() == inventory_btn) {
                inventory_form.setVisible(true);
                menu_form.setVisible(false);

            } else if (event.getSource() == menu_btn) {
                inventory_form.setVisible(false);
                menu_form.setVisible(true);

    
    
            }
        }

}
