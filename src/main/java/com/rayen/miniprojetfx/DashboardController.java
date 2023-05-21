package com.rayen.miniprojetfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import java.sql.Connection;
import connexion.connexion;
import javafx.scene.layout.GridPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardController {
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



    public GridPane getMenuGridPane() {
        return menu_gridPane;
    }

    public void initialize() {
        populateUserData();
    }

    //------------------------------------------Getting the data Form the database-------------------------------------------------
    private void populateUserData() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connexion.getConnection();
            if (connection != null) {
                System.out.println("Database Connected");
            }
            String query = "SELECT * FROM userpro";
            assert connection != null;
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            int row = 0;
            while (resultSet.next()) {
                int idP = resultSet.getInt("idP");
                String nomP = resultSet.getString("nomP");

                // Create labels to display the user information
                Label idLabel = new Label(String.valueOf(idP));
                Label nameLabel = new Label(nomP);
                Button addButton = new Button("Add");
                addButton.getStyleClass().add("add-button");
                addButton.setOnAction(event -> {
                    // Get the selected nomP when the button is clicked
                    String selectedNomP = nomP;

                    addNomPToTableView(selectedNomP);
                });
                menu_gridPane.addRow(row++, nameLabel, addButton);
            }
            menu_scrollPane.setContent(menu_gridPane);

        } catch (SQLException e) {
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

    //------------------------------------------Adding the product name and the quantity to the table view-------------------------------------------------
    private void addNomPToTableView(String nomP) {
        // Access the TableView using its fx:id (menu_tableView) in the FXML
        TableView<Product> tableView = menu_tableView;

        // Check if the nomP already exists in the TableView
        for (Product product : tableView.getItems()) {
            if (product.getName().equals(nomP)) {
                // Increment the quantity of the existing product
                product.setQuantity(product.getQuantity() + 1);

                // Refresh the TableView to reflect the changes
                tableView.refresh();
                return;
            }
        }
        Product newProduct = new Product(nomP, 1);

        tableView.getItems().add(newProduct);
        TableColumn<Product, String> nomPColumn = menu_col_productName;
        nomPColumn.setCellFactory(column -> new TableCell<Product, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item);
                }
                getStyleClass().add("table-cell-nomP");
            }
        });
        nomPColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Product, Integer> quantityColumn = menu_col_quantity;
        quantityColumn.setCellFactory(column -> new TableCell<Product, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.valueOf(item));
                }
                getStyleClass().add("table-cell-quantity");
            }
        });
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }







//------------------------------------------Switch Form-------------------------------------------------
    public void switchForm(ActionEvent event) {

        if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            inventory_form.setVisible(false);
            menu_form.setVisible(false);
            customers_form.setVisible(false);


        } else if (event.getSource() == inventory_btn) {
            dashboard_form.setVisible(false);
            inventory_form.setVisible(true);
            menu_form.setVisible(false);
            customers_form.setVisible(false);


        } else if (event.getSource() == menu_btn) {
            dashboard_form.setVisible(false);
            inventory_form.setVisible(false);
            menu_form.setVisible(true);
            customers_form.setVisible(false);


        } else if (event.getSource() == customers_btn) {
            dashboard_form.setVisible(false);
            inventory_form.setVisible(false);
            menu_form.setVisible(false);
            customers_form.setVisible(true);
        }
    }

}
