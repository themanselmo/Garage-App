package com.example.garageapp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class AccountBag implements Serializable {
    private ArrayList<UserAccount> accounts;
    private int nElements;

    public AccountBag(){
       accounts = new ArrayList<>();
       nElements = 0;
    }

    public UserAccount findAccount(String username) {
        UserAccount temp;
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUsername().equals(username)) {
                return accounts.get(i);
            }
        }
        return null;
    }

    public boolean insertAttendant(String username, String password) {
        UserAttendant insert = new UserAttendant(username,password);
        for (int i = 0; i < accounts.size(); i++) {
            if (username.equals(accounts.get(i).getUsername())) {
                System.out.println("Username already exists");
                return false;
            }
        }
        accounts.add(insert);
        System.out.println("Account added!");
        return true;
    }

    public boolean insertManager(String username, String password) {
        UserManager insert = new UserManager(username,password);
        for (int i = 0; i < accounts.size(); i++) {
            if (username.equals(accounts.get(i).getUsername())) {
                System.out.println("Username already exists");
                return false;
            }
        }
        accounts.add(insert);
        System.out.println("Account added!");
        return true;
    }

    // returns true if account is deleted
    public boolean deleteAccount(String username) {
        UserAccount temp;
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUsername().equals(username)) {
                accounts.remove(i);
                return true;
            }
        }
        return false;
    }

    public String displayAccounts(){
        String toBePrinted = "";
        for(int i = 0; i < accounts.size(); i++){
            toBePrinted += accounts.get(i).toString() + "\n";
        }
        return toBePrinted;
    }



}
