package com.smartplant.services;

import com.smartplant.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserService {
    private final ObservableList<User> users = FXCollections.observableArrayList(
        new User("US-001", "Nimal Perera", "nimal@example.com", "Admin", "Active"),
        new User("US-002", "Kamal Silva", "kamal@example.com", "Supervisor", "Active"),
        new User("US-003", "Amali Fernando", "amali@example.com", "Supervisor", "Active"),
        new User("US-004", "Sahan Jay", "sahan@example.com", "Supervisor", "Inactive")
    );
    public ObservableList<User> getUsers() { return users; }
}
