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
    private UserAccount currentUser;
    // payment rates used to calculate amount owed once car
    // is picked up
    private double motorcycleRate;
    private double carRate;
    private double truckRate;

    public Garage(int numberOfMotorCycleSpots, int numberOfCarSpots,
                  int numberOfTruckSpots) {
       setSpots(numberOfMotorCycleSpots,numberOfCarSpots,numberOfTruckSpots);
       callOutSpots();
       accountBag = new AccountBag();
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

    public ParkingSpot getSpot(int spotNumber){
        return parkingSpots.get(spotNumber);
    }

    private void setSpots(int numberOfMotorCycleSpots, int numberOfCarSpots, int numberOfTruckSpots) {
        totalSpots = numberOfCarSpots + numberOfMotorCycleSpots + numberOfTruckSpots;
        this.numberOfMotorcycleSpots = numberOfMotorCycleSpots;
        this.numberOfCarSpots = numberOfCarSpots;
        this.numberOfTruckSpots = numberOfTruckSpots;

        int count = 0;
        for (int i = 1; i < numberOfMotorCycleSpots+1; i++) {
            parkingSpots.add(new ParkingSpot(VehicleSize.Motorcycle, i));
            count = i;
        }
        count += 1;
        for (int i = count; i < (numberOfMotorCycleSpots + numberOfCarSpots) + 1; i++) {
            parkingSpots.add(new ParkingSpot(VehicleSize.Car, i));
            count = i;
        }
        count += 1;
        for (int i = count; i < (numberOfMotorCycleSpots + numberOfCarSpots +
                numberOfTruckSpots) + 1; i++) {
            parkingSpots.add(new ParkingSpot(VehicleSize.Truck, i));
        }

    }

    public int getAvailableSpots() {
        return availableSpots;
    }

    public int getTotalSpots() {
        return totalSpots;
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
        System.out.println(vehicle.getvSize() + " parked!");
        return true;
    }

    public boolean removeVehicleByPlateNumber(String plateNumber){
        ParkingSpot spotToRemoveFrom;
        for(int i = 0; i < totalSpots; i++){
            if(parkingSpots.get(i).getCurrentV() != null && parkingSpots.get(i).getCurrentV().getPlateNumber().equals(plateNumber)){
                spotToRemoveFrom = parkingSpots.get(i);
                spotToRemoveFrom.removeVehicle();
                System.out.println("Vehicle removed.");
                return true;
            }
        }
        System.out.println("Vehicle not found.");
        return false;
    }

    public boolean insertAttendant(String username, String password){
        if(accountBag.containsUsername(username) == true) {
            return false;
        } else {
            accountBag.insertAttendant(username, password);
            System.out.println("Account added with creds. : " + username + ", " + password);
            return true;
        }
    }

    public boolean insertAttendant(UserAttendant attendant){
        String username = attendant.getUsername();
        String password = attendant.getPassword();
        if(accountBag.containsUsername(username) == true) {
            return false;
        } else {
            accountBag.insertAttendant(username, password);
            System.out.println("Account added with creds. : " + username + ", " + password);
            return true;
        }
    }

    public boolean insertManager(String username, String password){
        if(accountBag.containsUsername(username) == true) {
            return false;
        } else {
            accountBag.insertManager(username, password);
            System.out.println("Account added with creds. : " + username + ", " + password);
            return true;
        }
    }

    public boolean insertManager(UserManager manager){
        String username = manager.getUsername();
        String password = manager.getPassword();
        if(accountBag.containsUsername(username) == true) {
            return false;
        } else {
            accountBag.insertManager(username, password);
            System.out.println("Account added with creds. : " + username + ", " + password);
            return true;
        }
    }

    public AccountBag getAccountBag() {
        return accountBag;
    }

    public UserAccount getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserAccount currentUser) {
        this.currentUser = currentUser;
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
