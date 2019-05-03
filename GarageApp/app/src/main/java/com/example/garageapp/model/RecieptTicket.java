package com.example.garageapp.model;

public class RecieptTicket extends Ticket {
    private double amountCharged;
    private String timeExit;
    // implement values for tax, total, grand total


    public RecieptTicket(String timeEntered, String timeExit, String paymentScheme) {
        calculateAmountDue(timeEntered, timeExit, paymentScheme);
    }

    // change time var to a time objecr
    public double calculateAmountDue(String timeEntered, String timeExit,
                                     String paymentScheme){


        return 0.0;
    }

}
