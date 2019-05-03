package com.example.garageapp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Garage implements Serializable {
    // list of parking spots in the garage, ordered by
    // motorcycles first, cars second, and trucks in the back
    private ArrayList<ParkingSpot> parkingSpots = new ArrayList<ParkingSpot>();
    // free (available) and total spots in the garagee
    private int availableSpots;
    private int totalSpots;
    private int numberOfMotorcycleSpots;
    private int numberOfCarSpots;
    private int numberOfTruckSpots;
    private AccountBag accountBag;
    // payment rates used to calculate amount owed once car
    // is picked up
    private double motorcycleRate;
    private double carRate;
    private double truckRate;

    public Garage(int numberOfMotorCycleSpots, int numberOfCarSpots,
                  int numberOfTruckSpots) {
       setSpots(numberOfMotorCycleSpots,numberOfCarSpots,numberOfTruckSpots);
       callOutSpots();
    }

    public Garage(int numberOfMotorCycleSpots, int numberOfCarSpots,
                  int numberOfTruckSpots, double motorcycleRate, double carRate,
                  double truckRate) {
        setSpots(numberOfMotorCycleSpots,numberOfCarSpots,numberOfTruckSpots);
        setPaymentRates(motorcycleRate, carRate, truckRate);
        callOutSpots();
        accountBag = new AccountBag();
        System.out.println("Garage Created...");
    }

    public void setPaymentRates(double motorcycleRate, double carRate, double truckRate){
        this.motorcycleRate = motorcycleRate;
        this.carRate = carRate;
        this.truckRate = truckRate;
    }

    public void callOutSpots(){
        System.out.println("Garage created! With respective spots " + "\n" +
                "Motorcycles : " + numberOfMotorcycleSpots +"\n" +
                "Cars : " + numberOfCarSpots +"\n" +
                "Trucks : " + numberOfTruckSpots +"\n" );
    }

    private void setSpots(int numberOfMotorCycleSpots, int numberOfCarSpots, int numberOfTruckSpots) {
        totalSpots = numberOfCarSpots + numberOfMotorCycleSpots + numberOfTruckSpots;
        this.numberOfMotorcycleSpots = numberOfMotorCycleSpots;
        this.numberOfCarSpots = numberOfCarSpots;
        this.numberOfTruckSpots = numberOfTruckSpots;

        int count = 0;
        for (int i = 0; i < numberOfMotorCycleSpots; i++) {
            parkingSpots.add(new ParkingSpot(VehicleSize.Motorcycle, i));
            count = i;
        }
        count += 1;
        for (int i = count; i < numberOfMotorCycleSpots + numberOfCarSpots; i++) {
            parkingSpots.add(new ParkingSpot(VehicleSize.Car, i));
            count = i;
        }
        count += 1;
        for (int i = count; i < numberOfMotorCycleSpots + numberOfCarSpots +
                numberOfTruckSpots; i++) {
            parkingSpots.add(new ParkingSpot(VehicleSize.Truck, i));
        }

    }

    public int getAvailableSpots() {
        return availableSpots;
    }

    public void DisplayParkingSpots() {
        for (int i = 0; i < totalSpots; i++) {
            System.out.print(parkingSpots.get(i).toString() + "\n");
        }
        System.out.println();
    }

    public int findSpotNearExit(Vehicle vehicle) {
        for (int i = 0; i < parkingSpots.size(); i++) {
            if (parkingSpots.get(i).getSpotSize() == vehicle.getvSize() &&
                    parkingSpots.get(i).isAvailable()) {
                return i;
            }
        }
        return -1;
    }

    public boolean parkVehicle(Vehicle vehicle) {
        int spotToBeParkedIn = findSpotNearExit(vehicle);
        if (spotToBeParkedIn == -1) {
            System.out.println("Spot not available.");
            return false;
        }
        parkingSpots.get(spotToBeParkedIn).parkVehicleInSpot(vehicle);
        return true;
    }

    public AccountBag getAccountBag() {
        return accountBag;
    }

    @Override
    public String toString() {
        return "Garage{" +
                "availableSpots=" + availableSpots +
                ", totalSpots=" + totalSpots +
                ", numberOfMotorcycleSpots=" + numberOfMotorcycleSpots +
                ", numberOfCarSpots=" + numberOfCarSpots +
                ", numberOfTruckSpots=" + numberOfTruckSpots +
                ", motorcycleRate=" + motorcycleRate +
                ", carRate=" + carRate +
                ", truckRate=" + truckRate +
                '}';
    }
}
