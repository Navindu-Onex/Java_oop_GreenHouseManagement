package com.smartplant.services;

import com.smartplant.models.Plant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PlantService {
    private final ObservableList<Plant> plants = FXCollections.observableArrayList(
        new Plant("PL-001", "Tomato", "Vegetable", 68, 6.5, 1.8, "Healthy"),
        new Plant("PL-002", "Bell Pepper", "Vegetable", 42, 6.1, 1.5, "Attention"),
        new Plant("PL-003", "Lettuce", "Leafy", 74, 6.4, 1.2, "Healthy"),
        new Plant("PL-004", "Cucumber", "Vegetable", 31, 5.8, 1.9, "Critical"),
        new Plant("PL-005", "Spinach", "Leafy", 63, 6.7, 1.4, "Healthy"),
        new Plant("PL-006", "Strawberry", "Fruit", 55, 6.2, 1.6, "Healthy")
    );
    public ObservableList<Plant> getPlants() { return plants; }
    public long countHealthy() { return plants.stream().filter(p -> p.getStatus().equals("Healthy")).count(); }
    public long countAttention() { return plants.stream().filter(p -> !p.getStatus().equals("Healthy")).count(); }
}
