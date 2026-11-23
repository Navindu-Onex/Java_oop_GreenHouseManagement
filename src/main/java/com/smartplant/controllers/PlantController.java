package com.smartplant.controllers;

import com.smartplant.models.Plant;
import com.smartplant.services.PlantService;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.ReadOnlyStringWrapper;

import java.util.function.Consumer;

public class PlantController {
    @FXML private TextField searchField;
    @FXML private ComboBox<String> statusFilter;
    @FXML private TableView<Plant> plantsTable;
    @FXML private TableColumn<Plant, String> plantIdColumn, plantNameColumn, plantTypeColumn;
    @FXML private TableColumn<Plant, String> moistureColumn, phColumn, ecColumn, plantStatusColumn;

    private final PlantService service = new PlantService();
    private Consumer<Plant> plantSelectionHandler;

    public void setPlantSelectionHandler(Consumer<Plant> handler) {
        this.plantSelectionHandler = handler;
    }

    @FXML
    private void initialize() {
        plantIdColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getId()));
        plantNameColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getName()));
        plantTypeColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getType()));
        moistureColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getMoisture() + " %"));
        phColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(String.valueOf(c.getValue().getPh())));
        ecColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getEc() + " mS/cm"));
        plantStatusColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getStatus()));

        FilteredList<Plant> filtered = new FilteredList<>(service.getPlants(), p -> true);
        plantsTable.setItems(filtered);
        statusFilter.getItems().setAll("All", "Healthy", "Attention", "Critical");
        statusFilter.setValue("All");

        Runnable applyFilter = () -> {
            String query = searchField.getText() == null ? "" : searchField.getText().toLowerCase().trim();
            String status = statusFilter.getValue();
            filtered.setPredicate(p ->
                (query.isEmpty() || p.getName().toLowerCase().contains(query) || p.getId().toLowerCase().contains(query))
                && (status == null || status.equals("All") || p.getStatus().equals(status))
            );
        };
        searchField.textProperty().addListener((obs, oldValue, newValue) -> applyFilter.run());
        statusFilter.valueProperty().addListener((obs, oldValue, newValue) -> applyFilter.run());

        plantsTable.setRowFactory(tv -> {
            TableRow<Plant> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty() && plantSelectionHandler != null) {
                    plantSelectionHandler.accept(row.getItem());
                }
            });
            return row;
        });
    }
}
