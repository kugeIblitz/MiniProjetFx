package com.rayen.miniprojetfx;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


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
    private Button logout_btn;

    @FXML
    private AnchorPane inventory_form;

//    @FXML
//    private TableView<productData> inventory_tableView;

//    @FXML
//    private TableColumn<productData, String> inventory_col_productID;
//
//    @FXML
//    private TableColumn<productData, String> inventory_col_productName;
//
//    @FXML
//    private TableColumn<productData, String> inventory_col_type;
//
//    @FXML
//    private TableColumn<productData, String> inventory_col_stock;
//
//    @FXML
//    private TableColumn<productData, String> inventory_col_price;
//
//    @FXML
//    private TableColumn<productData, String> inventory_col_status;
//
//    @FXML
//    private TableColumn<productData, String> inventory_col_date;

    @FXML
    private ImageView inventory_imageView;

    @FXML
    private Button inventory_importBtn;

    @FXML
    private Button inventory_addBtn;

    @FXML
    private Button inventory_updateBtn;

    @FXML
    private Button inventory_clearBtn;

    @FXML
    private Button inventory_deleteBtn;

    @FXML
    private TextField inventory_productID;

    @FXML
    private TextField inventory_productName;

    @FXML
    private TextField inventory_stock;

    @FXML
    private TextField inventory_price;

    @FXML
    private ComboBox<?> inventory_status;

    @FXML
    private ComboBox<?> inventory_type;

    @FXML
    private AnchorPane menu_form;

    @FXML
    private ScrollPane menu_scrollPane;

    @FXML
    private GridPane menu_gridPane;

//    @FXML
//    private TableView<productData> menu_tableView;
//
//    @FXML
//    private TableColumn<productData, String> menu_col_productName;
//
//    @FXML
//    private TableColumn<productData, String> menu_col_quantity;
//
//    @FXML
//    private TableColumn<productData, String> menu_col_price;

    @FXML
    private Label menu_total;

    @FXML
    private TextField menu_amount;

    @FXML
    private Label menu_change;

    @FXML
    private Button menu_payBtn;

    @FXML
    private Button menu_removeBtn;

    @FXML
    private Button menu_receiptBtn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private AnchorPane customers_form;

//    @FXML
//    private TableView<customersData> customers_tableView;
//
//    @FXML
//    private TableColumn<customersData, String> customers_col_customerID;
//
//    @FXML
//    private TableColumn<customersData, String> customers_col_total;
//
//    @FXML
//    private TableColumn<customersData, String> customers_col_date;
//
//    @FXML
//    private TableColumn<customersData, String> customers_col_cashier;

    @FXML
    private Label dashboard_NC;

    @FXML
    private Label dashboard_TI;

    @FXML
    private Label dashboard_TotalI;

    @FXML
    private Label dashboard_NSP;

    @FXML
    private AreaChart<?, ?> dashboard_incomeChart;

    @FXML
    private BarChart<?, ?> dashboard_CustomerChart;

    private Alert alert;

//    private Connection connect;
//    private PreparedStatement prepare;
//    private Statement statement;
//    private ResultSet result;

    private Image image;

//    private ObservableList<productData> cardListData = FXCollections.observableArrayList();

    public void switchForm(ActionEvent event) {

        if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            inventory_form.setVisible(false);
            menu_form.setVisible(false);
            customers_form.setVisible(false);

            dashboardDisplayNC();
            dashboardDisplayTI();
            dashboardTotalI();
            dashboardNSP();
            dashboardIncomeChart();
            dashboardCustomerChart();

        } else if (event.getSource() == inventory_btn) {
            dashboard_form.setVisible(false);
            inventory_form.setVisible(true);
            menu_form.setVisible(false);
            customers_form.setVisible(false);

            inventoryTypeList();
            inventoryStatusList();
            inventoryShowData();
        } else if (event.getSource() == menu_btn) {
            dashboard_form.setVisible(false);
            inventory_form.setVisible(false);
            menu_form.setVisible(true);
            customers_form.setVisible(false);

            menuDisplayCard();
            menuDisplayTotal();
            menuShowOrderData();
        } else if (event.getSource() == customers_btn) {
            dashboard_form.setVisible(false);
            inventory_form.setVisible(false);
            menu_form.setVisible(false);
            customers_form.setVisible(true);

            customersShowData();
        }

    }

    private void dashboardCustomerChart() {
    }

    private void dashboardIncomeChart() {
    }

    private void dashboardNSP() {
    }

    private void dashboardTotalI() {
    }

    private void dashboardDisplayTI() {
    }

    private void inventoryTypeList() {
    }

    private void inventoryStatusList() {
    }

    private void inventoryShowData() {
    }

    private void menuShowOrderData() {
    }

    private void menuDisplayTotal() {
    }

    private void menuDisplayCard() {
    }

    private void customersShowData() {
    }

    private void dashboardDisplayNC() {
    }
}
