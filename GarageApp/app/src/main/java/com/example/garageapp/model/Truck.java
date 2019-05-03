package com.example.garageapp.model;

public class Truck extends Vehicle {

    public Truck(){
        vSize = VehicleSize.Truck;
    }

    @Override
    public boolean canFitInSpot(ParkingSpot pSpot){
        return pSpot.getSpotSize() == vSize;
    }
}
