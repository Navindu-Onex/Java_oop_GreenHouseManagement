package com.smartplant.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.util.function.Consumer;

public class NavigationController {
    @FXML private Button dashboardButton;
    @FXML private Button plantsButton;
    @FXML private Button monitoringButton;
    @FXML private Button usersButton;
    @FXML private Button alertsButton;
    @FXML private Button settingsButton;

    private Consumer<String> pageNavigator;

    public void setPageNavigator(Consumer<String> pageNavigator) {
        this.pageNavigator = pageNavigator;
        setActive(dashboardButton);
    }

    @FXML private void openDashboard() { navigate("dashboard", dashboardButton); }
    @FXML private void openPlants() { navigate("plants", plantsButton); }
    @FXML private void openMonitoring() { navigate("monitoring", monitoringButton); }
    @FXML private void openUsers() { navigate("users", usersButton); }
    @FXML private void openAlerts() { navigate("alerts", alertsButton); }
    @FXML private void openSettings() { navigate("settings", settingsButton); }

    private void navigate(String page, Button selected) {
        if (pageNavigator != null) {
            pageNavigator.accept(page);
            setActive(selected);
        }
    }

    private void setActive(Button selected) {
        Button[] buttons = {dashboardButton, plantsButton, monitoringButton,
                usersButton, alertsButton, settingsButton};
        for (Button button : buttons) {
            if (button != null) button.getStyleClass().remove("nav-button-active");
        }
        if (selected != null && !selected.getStyleClass().contains("nav-button-active")) {
            selected.getStyleClass().add("nav-button-active");
        }
    }

    @FXML
    private void logout() {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Logout");
        confirm.setHeaderText("Exit SmartGreen?");
        confirm.setContentText("This demo application will close.");
        confirm.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) Platform.exit();
        });
    }
}
