package com.smartplant.models;

public class Plant {
    private final String id;
    private String name;
    private String type;
    private double moisture;
    private double ph;
    private double ec;
    private String status;

    public Plant(String id, String name, String type, double moisture, double ph, double ec, String status) {
        this.id = id; this.name = name; this.type = type;
        this.moisture = moisture; this.ph = ph; this.ec = ec; this.status = status;
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public double getMoisture() { return moisture; }
    public double getPh() { return ph; }
    public double getEc() { return ec; }
    public String getStatus() { return status; }
    public void setName(String name) { this.name = name; }
    public void setMoisture(double moisture) { this.moisture = moisture; }
    public void setPh(double ph) { this.ph = ph; }
    public void setEc(double ec) { this.ec = ec; }
    public void setStatus(String status) { this.status = status; }
}
