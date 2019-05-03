package com.example.garageapp.model;

public class UserManager extends UserAccount {

    public UserManager(String username, String password){
        this.setUsername(username);
        this.setPassword(password);
        this.setAccountType(AccountType.Manager);
    }
}
