package com.smartplant.services;

import java.util.Random;

public class SensorService {
    private final Random random = new Random();
    public double getTemperature() { return round(28.5 + random.nextDouble() * 1.2); }
    public double getHumidity() { return round(64 + random.nextDouble() * 3); }
    public int getLight() { return 720 + random.nextInt(80); }
    public int getCo2() { return 410 + random.nextInt(35); }
    private double round(double value) { return Math.round(value * 10.0) / 10.0; }
}
