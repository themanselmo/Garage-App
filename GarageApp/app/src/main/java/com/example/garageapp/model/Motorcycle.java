package com.example.garageapp.model;

public class Motorcycle extends Vehicle {

    public Motorcycle(){
        vSize = VehicleSize.Motorcycle;
    }

    @Override
    public boolean canFitInSpot(ParkingSpot pSpot) {
        return pSpot.getSpotSize() == vSize;
    }
}
