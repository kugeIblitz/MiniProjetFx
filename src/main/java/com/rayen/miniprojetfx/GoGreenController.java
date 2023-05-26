package com.rayen.miniprojetfx;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class GoGreenController {

    @FXML
    private TableColumn colDescription;

    @FXML
    private TableView<Product> tableView;

    @FXML
    private TableColumn<Product, String> colProductName;

    @FXML
    private TableColumn<Product, Double> colYouSaved;

    public void initialize() {
        // Set up the table columns
        colProductName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
//        colYouSaved.setCellValueFactory(cellData ->cellData.getValue().quantityProperty());
    }

    public void setGreenProducts(ObservableList<Product> products) {
        tableView.setItems(products);
    }
}
