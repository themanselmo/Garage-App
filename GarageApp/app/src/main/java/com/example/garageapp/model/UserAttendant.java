package com.example.garageapp.model;

public class UserAttendant extends UserAccount {

    public UserAttendant(String username, String password){
        this.setUsername(username);
        this.setPassword(password);
        this.setAccountType(AccountType.Attendant);
    }
}
