package com.smartplant.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SettingsController {
    @FXML private TextField maxTemperatureField, minTemperatureField, maxHumidityField, minHumidityField;
    @FXML private CheckBox emailNotifications, criticalAlerts, sensorNotifications;

    @FXML
    private void initialize() {
        emailNotifications.setSelected(true);
        criticalAlerts.setSelected(true);
        sensorNotifications.setSelected(true);
    }

    @FXML
    private void saveSettings() {
        try {
            double maxTemp = Double.parseDouble(maxTemperatureField.getText());
            double minTemp = Double.parseDouble(minTemperatureField.getText());
            double maxHumidity = Double.parseDouble(maxHumidityField.getText());
            double minHumidity = Double.parseDouble(minHumidityField.getText());

            if (minTemp >= maxTemp || minHumidity >= maxHumidity) {
                showMessage(Alert.AlertType.WARNING, "Invalid thresholds",
                        "Minimum values must be lower than maximum values.");
                return;
            }
            showMessage(Alert.AlertType.INFORMATION, "Settings saved",
                    "Demo settings validated successfully. Persistent storage is not connected yet.");
        } catch (NumberFormatException ex) {
            showMessage(Alert.AlertType.ERROR, "Invalid input", "Please enter valid numeric threshold values.");
        }
    }

    private void showMessage(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
