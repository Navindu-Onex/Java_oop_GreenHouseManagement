package com.smartplant.models;

public class Admin extends User {
    public Admin(String id, String name, String email, String status) {
        super(id, name, email, "Admin", status);
    }
}
