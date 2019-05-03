package com.example.garageapp.model;

public class Car extends Vehicle {

    public Car(){
        vSize = VehicleSize.Car;
    }

    @Override
    public boolean canFitInSpot(ParkingSpot pSpot){
        return pSpot.getSpotSize() == vSize;
    }
}
