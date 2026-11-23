package com.smartplant.models;

public class User extends Person {
    private final String email;
    private final String role;
    private final String status;

    public User(String id, String name, String email, String role, String status) {
        super(id, name);
        this.email = email;
        this.role = role;
        this.status = status;
    }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public String getStatus() { return status; }
}
