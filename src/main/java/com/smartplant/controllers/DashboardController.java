package com.smartplant.controllers;

import com.smartplant.models.Alert;
import com.smartplant.services.AlertService;
import com.smartplant.services.PlantService;
import com.smartplant.services.SensorService;
import com.smartplant.services.UserService;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.ReadOnlyStringWrapper;

public class DashboardController {
    @FXML private Label temperatureLabel, humidityLabel, lightLabel, co2Label;
    @FXML private Label totalPlantsLabel, healthyPlantsLabel, attentionPlantsLabel, totalUsersLabel;
    @FXML private LineChart<String, Number> environmentChart;
    @FXML private PieChart plantHealthChart;
    @FXML private TableView<Alert> alertsTable;
    @FXML private TableColumn<Alert, String> alertTimeColumn, alertPlantColumn, alertMessageColumn, alertSeverityColumn;

    private final SensorService sensors = new SensorService();
    private final PlantService plants = new PlantService();
    private final UserService users = new UserService();
    private final AlertService alerts = new AlertService();

    @FXML
    private void initialize() {
        updateCards();
        totalPlantsLabel.setText(String.valueOf(plants.getPlants().size()));
        healthyPlantsLabel.setText(String.valueOf(plants.countHealthy()));
        attentionPlantsLabel.setText(String.valueOf(plants.countAttention()));
        totalUsersLabel.setText(String.valueOf(users.getUsers().size()));

        alertTimeColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getTime()));
        alertPlantColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getSource()));
        alertMessageColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getDescription()));
        alertSeverityColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getSeverity()));
        alertsTable.setItems(alerts.getAlerts());

        XYChart.Series<String, Number> temperature = new XYChart.Series<>();
        temperature.setName("Temperature °C");
        temperature.getData().addAll(
            new XYChart.Data<>("08:00", 26), new XYChart.Data<>("09:00", 27),
            new XYChart.Data<>("10:00", 28), new XYChart.Data<>("11:00", 28.5),
            new XYChart.Data<>("12:00", 29)
        );
        XYChart.Series<String, Number> humidity = new XYChart.Series<>();
        humidity.setName("Humidity %");
        humidity.getData().addAll(
            new XYChart.Data<>("08:00", 72), new XYChart.Data<>("09:00", 70),
            new XYChart.Data<>("10:00", 68), new XYChart.Data<>("11:00", 65),
            new XYChart.Data<>("12:00", 66)
        );
        environmentChart.getData().setAll(temperature, humidity);

        plantHealthChart.getData().setAll(
            new PieChart.Data("Healthy", plants.countHealthy()),
            new PieChart.Data("Attention", plants.countAttention())
        );
    }

    private void updateCards() {
        temperatureLabel.setText(sensors.getTemperature() + " °C");
        humidityLabel.setText(sensors.getHumidity() + " %");
        lightLabel.setText(sensors.getLight() + " lux");
        co2Label.setText(sensors.getCo2() + " ppm");
    }
}
