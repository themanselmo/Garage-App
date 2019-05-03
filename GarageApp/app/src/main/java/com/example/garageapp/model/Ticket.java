package com.example.garageapp.model;

public abstract class Ticket {
    private String plateNumber;
    private String categoryOfSpot;
    private String nameOfAttendant;
    private String paymentScheme;
    private String date;
    private String timeEntered;

    public String getTimeEntered() {
        return timeEntered;
    }

    public void setTimeEntered(String timeEntered) {
        this.timeEntered = timeEntered;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getCategoryOfSpot() {
        return categoryOfSpot;
    }

    public void setCategoryOfSpot(String categoryOfSpot) {
        this.categoryOfSpot = categoryOfSpot;
    }

    public String getNameOfAttendant() {
        return nameOfAttendant;
    }

    public void setNameOfAttendant(String nameOfAttendant) {
        this.nameOfAttendant = nameOfAttendant;
    }

    public String getPaymentScheme() {
        return paymentScheme;
    }

    public void setPaymentScheme(String paymentScheme) {
        this.paymentScheme = paymentScheme;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
