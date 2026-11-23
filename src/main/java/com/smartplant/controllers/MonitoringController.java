package com.smartplant.controllers;

import com.smartplant.services.SensorService;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Label;

public class MonitoringController {
    @FXML private Label monitorTemperature, monitorHumidity, monitorLight, monitorCO2;
    @FXML private LineChart<String, Number> monitoringChart;

    @FXML
    private void initialize() {
        SensorService sensors = new SensorService();
        monitorTemperature.setText(sensors.getTemperature() + " °C");
        monitorHumidity.setText(sensors.getHumidity() + " %");
        monitorLight.setText(sensors.getLight() + " lux");
        monitorCO2.setText(sensors.getCo2() + " ppm");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Temperature °C");
        series.getData().addAll(
            new XYChart.Data<>("08:00", 26.2), new XYChart.Data<>("09:00", 27.1),
            new XYChart.Data<>("10:00", 27.8), new XYChart.Data<>("11:00", 28.5),
            new XYChart.Data<>("12:00", 29.0)
        );
        monitoringChart.getData().setAll(series);
    }
}
