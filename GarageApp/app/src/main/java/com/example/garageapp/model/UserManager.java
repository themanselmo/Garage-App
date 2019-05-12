package com.example.garageapp.model;

import java.io.Serializable;

public class UserManager extends UserAccount{

    public UserManager(String username, String password){
        this.setUsername(username);
        this.setPassword(password);
        this.setAccountType(AccountType.Manager);
    }
}
