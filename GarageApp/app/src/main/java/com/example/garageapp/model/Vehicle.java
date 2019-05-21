package com.example.garageapp.model;

import java.io.Serializable;

public abstract class Vehicle implements Serializable {
    protected String plateNumber;
    protected VehicleSize vSize;
    protected double costRate;
    protected long timeParked;
    protected long timeRemoved;
    public abstract boolean canFitInSpot(ParkingSpot pSpot);

    public void setvSize(VehicleSize vSize) {
        this.vSize = vSize;
    }

    public long getTimeParked() {
        return timeParked;
    }

    public void setTimeParked(long timeParked) {
        this.timeParked = timeParked;
    }

    public long getTimeRemoved() {
        return timeRemoved;
    }

    public void setTimeRemoved(long timeRemoved) {
        this.timeRemoved = timeRemoved;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public VehicleSize getvSize() {
        return vSize;
    }

    public void setCostRate(double costRate){
        this.costRate = costRate;
    }

    public double getCostRate(){
        return costRate;
    }
}

