package com.smartplant.services;

import com.smartplant.models.Alert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AlertService {
    private final ObservableList<Alert> alerts = FXCollections.observableArrayList(
        new Alert("AL-001", "09:42", "Greenhouse", "Temperature exceeded 35 °C", "Critical", "Active"),
        new Alert("AL-002", "09:18", "Cucumber · PL-004", "Soil moisture is below 35%", "Warning", "Active"),
        new Alert("AL-003", "08:55", "Bell Pepper · PL-002", "Soil moisture is decreasing", "Warning", "Active"),
        new Alert("AL-004", "Yesterday", "Greenhouse", "Humidity returned to normal", "Info", "Resolved")
    );
    public ObservableList<Alert> getAlerts() { return alerts; }
    public void markAllRead() { alerts.forEach(a -> a.setStatus("Read")); }
}
