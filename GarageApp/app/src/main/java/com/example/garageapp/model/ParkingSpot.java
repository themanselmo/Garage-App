package com.example.garageapp.model;

import java.io.Serializable;

public class ParkingSpot implements Serializable {
    private Vehicle currentV;
    private VehicleSize spotSize;
    private int spotNumber;
    private String whoParked;
    private int distanceFromExit; // counting by spots

    // constructor for parkingSpot
    public ParkingSpot(VehicleSize size, int spotNumber){
        spotSize = size;
        this.spotNumber = spotNumber;
    }

    // check if parking spot is available
    public boolean isAvailable(){
        return currentV == null;
    }

    // check if vehicle can fit in parking spot
    public boolean canFitVehicle(Vehicle vehicle){
        return isAvailable() && vehicle.canFitInSpot(this);
    }

    /*
    park vehicle in spot
    @param Vehicle: Vehicle to be parked
    @return true if car is parked and false if not
     */
    public boolean parkVehicleInSpot(Vehicle vehicle){
        if(canFitVehicle(vehicle)){
            currentV = vehicle;
            return true;
        }
        System.out.println("Spot not available.");
        return false;

    }

    // remove vehicle from spot
    public void removeVehicle() {
        currentV = null;
    }

    public VehicleSize getSpotSize() {
        return spotSize;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public Vehicle getCurrentV() {
        return currentV;
    }

    public String getWhoParked() {
        return whoParked;
    }

    public void setWhoParked(String whoParked) {
        this.whoParked = whoParked;
    }

    @Override
    public String toString() {
        String returnString;
        if(currentV != null){
            returnString = "ParkingSpot: " + spotNumber + "\n" +
                    "Parked By: " + whoParked + "\n" +
                    "Current Vehicle: " + currentV.getvSize() + "\n" +
                    "Plate Number: " +currentV.getPlateNumber() + "\n" +
                    "Spot Size: " + spotSize;
        } else {
            returnString = "ParkingSpot: " + spotNumber + "\n" +
                    "Current Vehicle: " + currentV + "\n" +
                    "Spot Size: " + spotSize + "\n";
        }

        return returnString;
    }

}
