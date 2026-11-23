package com.smartplant.controllers;

import com.smartplant.models.Alert;
import com.smartplant.services.AlertService;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.ReadOnlyStringWrapper;

public class AlertController {
    @FXML private ComboBox<String> severityFilter, alertStatusFilter;
    @FXML private TableView<Alert> alertsTable;
    @FXML private TableColumn<Alert, String> alertIdColumn, alertTimeColumn, alertSourceColumn,
            alertDescriptionColumn, alertLevelColumn, alertStateColumn;

    private final AlertService service = new AlertService();

    @FXML
    private void initialize() {
        alertIdColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getId()));
        alertTimeColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getTime()));
        alertSourceColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getSource()));
        alertDescriptionColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getDescription()));
        alertLevelColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getSeverity()));
        alertStateColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getStatus()));

        FilteredList<Alert> filtered = new FilteredList<>(service.getAlerts(), a -> true);
        alertsTable.setItems(filtered);
        severityFilter.getItems().setAll("All", "Critical", "Warning", "Info");
        alertStatusFilter.getItems().setAll("All", "Active", "Resolved", "Read");
        severityFilter.setValue("All");
        alertStatusFilter.setValue("All");

        Runnable applyFilter = () -> {
            String severity = severityFilter.getValue();
            String status = alertStatusFilter.getValue();
            filtered.setPredicate(a ->
                (severity == null || severity.equals("All") || a.getSeverity().equals(severity))
                && (status == null || status.equals("All") || a.getStatus().equals(status))
            );
        };
        severityFilter.valueProperty().addListener((obs, oldValue, newValue) -> applyFilter.run());
        alertStatusFilter.valueProperty().addListener((obs, oldValue, newValue) -> applyFilter.run());
    }

    @FXML
    private void markAllRead() {
        service.markAllRead();
        alertsTable.refresh();
        alertStatusFilter.setValue("All");
    }
}
