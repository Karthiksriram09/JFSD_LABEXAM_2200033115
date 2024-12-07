package com.klef.jfsd.exam;

import javax.persistence.Entity;

@Entity
public class Truck extends Vehicle {
    private double loadCapacity;

    // Getters and setters
    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }
}
