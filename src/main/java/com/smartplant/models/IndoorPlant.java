package com.smartplant.models;

public class IndoorPlant extends Plant {
    public IndoorPlant(String id, String name, double moisture, double ph, double ec, String status) {
        super(id, name, "Indoor", moisture, ph, ec, status);
    }
}
