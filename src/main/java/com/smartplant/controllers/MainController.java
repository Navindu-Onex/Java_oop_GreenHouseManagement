package com.smartplant.controllers;

import com.smartplant.models.Plant;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainController {
    @FXML private StackPane contentArea;
    @FXML private NavigationController sidebarController;

    @FXML
    private void initialize() {
        sidebarController.setPageNavigator(this::loadPage);
        loadPage("dashboard");
    }

    public void loadPage(String pageName) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/" + pageName + ".fxml")
            );
            Parent page = loader.load();

            Object controller = loader.getController();
            if (controller instanceof PlantController plantController) {
                plantController.setPlantSelectionHandler(this::showPlantDetails);
            }

            contentArea.getChildren().setAll(page);
        } catch (IOException | RuntimeException ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Page loading error");
            alert.setHeaderText("Could not open " + pageName);
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }

    private void showPlantDetails(Plant plant) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/plant-details.fxml")
            );
            Parent page = loader.load();
            PlantDetailsController controller = loader.getController();
            controller.setPlant(plant);
            controller.setBackAction(() -> loadPage("plants"));
            contentArea.getChildren().setAll(page);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
