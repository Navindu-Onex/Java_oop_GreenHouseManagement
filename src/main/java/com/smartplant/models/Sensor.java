package com.smartplant.models;

public abstract class Sensor {
    private final String name;
    private final String unit;
    private double value;

    protected Sensor(String name, String unit, double value) {
        this.name = name;
        this.unit = unit;
        this.value = value;
    }
    public String getName() { return name; }
    public String getUnit() { return unit; }
    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }
}
