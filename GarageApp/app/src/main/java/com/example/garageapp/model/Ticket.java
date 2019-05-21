package com.example.garageapp.model;

import java.io.Serializable;
import java.util.Date;

public abstract class Ticket implements Serializable {
    private String plateNumber;
    private int spotNumber;
    private VehicleSize categoryOfSpot;
    private String nameOfAttendant;
    private double paymentScheme;
    private Date date;
    private long timeEntered;

    public Ticket() {

    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(int spotNumber) {
        this.spotNumber = spotNumber;
    }

    public long getTimeEntered() {
        return timeEntered;
    }

    public void setTimeEntered(long timeEntered) {
        this.timeEntered = timeEntered;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public VehicleSize getCategoryOfSpot() {
        return categoryOfSpot;
    }

    public void setCategoryOfSpot(VehicleSize categoryOfSpot) {
        this.categoryOfSpot = categoryOfSpot;
    }

    public String getNameOfAttendant() {
        return nameOfAttendant;
    }

    public void setNameOfAttendant(String nameOfAttendant) {
        this.nameOfAttendant = nameOfAttendant;
    }

    public double getPaymentScheme() {
        return paymentScheme;
    }

    public void setPaymentScheme(double paymentScheme) {
        this.paymentScheme = paymentScheme;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
