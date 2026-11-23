package com.smartplant.models;

public class OutdoorPlant extends Plant {
    public OutdoorPlant(String id, String name, double moisture, double ph, double ec, String status) {
        super(id, name, "Outdoor", moisture, ph, ec, status);
    }
}
