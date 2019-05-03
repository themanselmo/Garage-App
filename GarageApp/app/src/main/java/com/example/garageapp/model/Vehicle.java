package com.example.garageapp.model;

public abstract class Vehicle {
    protected String plateNumber;
    protected VehicleSize vSize;
    protected double costRate;

    public abstract boolean canFitInSpot(ParkingSpot pSpot);

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

