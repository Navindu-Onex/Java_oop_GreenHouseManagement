package com.smartplant.controllers;

import com.smartplant.models.Plant;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Label;

public class PlantDetailsController {
    @FXML private Label plantNameLabel, plantIdLabel, plantStatusLabel, moistureLabel, phLabel, ecLabel;
    @FXML private LineChart<String, Number> plantHistoryChart;
    private Runnable backAction;

    public void setBackAction(Runnable backAction) { this.backAction = backAction; }

    public void setPlant(Plant plant) {
        plantNameLabel.setText(plant.getName());
        plantIdLabel.setText("Plant ID: " + plant.getId() + "  |  Type: " + plant.getType());
        plantStatusLabel.setText("● " + plant.getStatus());
        moistureLabel.setText(plant.getMoisture() + " %");
        phLabel.setText(String.valueOf(plant.getPh()));
        ecLabel.setText(plant.getEc() + " mS/cm");
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Soil moisture %");
        series.getData().addAll(
            new XYChart.Data<>("08:00", plant.getMoisture() - 8),
            new XYChart.Data<>("09:00", plant.getMoisture() - 5),
            new XYChart.Data<>("10:00", plant.getMoisture() - 3),
            new XYChart.Data<>("11:00", plant.getMoisture() - 1),
            new XYChart.Data<>("12:00", plant.getMoisture())
        );
        plantHistoryChart.getData().setAll(series);
    }

    @FXML private void backToPlants() {
        if (backAction != null) backAction.run();
    }
}
