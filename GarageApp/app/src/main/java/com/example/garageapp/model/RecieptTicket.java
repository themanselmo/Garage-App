package com.example.garageapp.model;

public class RecieptTicket extends Ticket {
    private double amountCharged;
    private long timeExit;
    // implement values for tax, total, grand total


    public RecieptTicket() {

    }

    // change time var to a time objecr
    public void calculateAmountDue(long timeEntered, long timeExit,
                                     double paymentScheme){
        double total;
        long elapsedTime = timeExit - timeEntered;
        int hoursPassed = (int) ((elapsedTime / (1000*60*60)) % 24);
        if(hoursPassed < 1) {
            total = paymentScheme;
        } else {
            total = hoursPassed * paymentScheme;
        }
        setAmountCharged(total);
    }

    public double getAmountCharged() {
        return amountCharged;
    }

    public void setAmountCharged(double amountCharged) {
        this.amountCharged = amountCharged;
    }

    public long getTimeExit() {
        return timeExit;
    }

    public void setTimeExit(long timeExit) {
        this.timeExit = timeExit;
    }
}
