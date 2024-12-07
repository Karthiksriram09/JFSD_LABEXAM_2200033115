package com.klef.jfsd.exam;

import javax.persistence.Entity;

@Entity
public class Car extends Vehicle {
    private int numberOfDoors;

    // Getters and setters
    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }
}
