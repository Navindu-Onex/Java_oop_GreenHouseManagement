package com.smartplant.controllers;

import com.smartplant.models.User;
import com.smartplant.services.UserService;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.ReadOnlyStringWrapper;

public class UserController {
    @FXML private TextField userSearchField;
    @FXML private ComboBox<String> roleFilter;
    @FXML private TableView<User> usersTable;
    @FXML private TableColumn<User, String> userIdColumn, userNameColumn, emailColumn, roleColumn, userStatusColumn;

    private final UserService service = new UserService();

    @FXML
    private void initialize() {
        userIdColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getId()));
        userNameColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getName()));
        emailColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getEmail()));
        roleColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getRole()));
        userStatusColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getStatus()));

        FilteredList<User> filtered = new FilteredList<>(service.getUsers(), u -> true);
        usersTable.setItems(filtered);
        roleFilter.getItems().setAll("All", "Admin", "Supervisor");
        roleFilter.setValue("All");

        Runnable applyFilter = () -> {
            String query = userSearchField.getText() == null ? "" : userSearchField.getText().toLowerCase().trim();
            String role = roleFilter.getValue();
            filtered.setPredicate(u ->
                (query.isEmpty() || u.getName().toLowerCase().contains(query) || u.getEmail().toLowerCase().contains(query))
                && (role == null || role.equals("All") || u.getRole().equals(role))
            );
        };
        userSearchField.textProperty().addListener((obs, oldValue, newValue) -> applyFilter.run());
        roleFilter.valueProperty().addListener((obs, oldValue, newValue) -> applyFilter.run());
    }
}
