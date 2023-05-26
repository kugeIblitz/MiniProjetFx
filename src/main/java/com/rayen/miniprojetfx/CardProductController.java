package com.rayen.miniprojetfx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CardProductController implements Initializable {

    @FXML
    private AnchorPane card_form;

    @FXML
    private VBox card;

    @FXML
    private HBox productDetails;

    @FXML
    private Label prod_name;

    @FXML
    private Label prod_price;

    @FXML
    private ImageView prod_imageView;

    @FXML
    private Spinner<Integer> prod_spinner;

    @FXML
    private Button prod_addBtn;

    private Product product;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setProductData(String productName, String imageLink) {
        // Set the product name label
        prod_name.setText(productName);

        // Load and display the image
        Image image = new Image(imageLink);
        prod_imageView.setImage(image);

        // You can populate the other product details and configure the UI as needed
    }

}
