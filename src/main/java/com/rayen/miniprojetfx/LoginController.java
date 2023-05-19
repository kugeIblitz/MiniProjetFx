package com.rayen.miniprojetfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    public Button loginBtn;
    @FXML
    public void openDashboard(ActionEvent event) {
        try {
            // Get the button's scene
            Scene currentScene = ((Node) event.getSource()).getScene();

            // Create a new FXMLLoader
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
            Parent root = loader.load();
            Scene dashboardScene = new Scene(root);

            // Get the current stage
            Stage stage = (Stage) currentScene.getWindow();

            // Set the dashboard scene on the stage
            stage.setScene(dashboardScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

