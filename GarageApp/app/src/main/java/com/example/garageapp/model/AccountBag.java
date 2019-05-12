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

    // returns true if username exists
    public boolean containsUsername(String username){
        for (int i = 0; i < accounts.size(); i++) {
            if (username.equals(accounts.get(i).getUsername())) {
                System.out.println("Username already exists");
                return true;
            }
        }
        return false;
    }

    public void insertAttendant(String username, String password) {
        UserAttendant insert = new UserAttendant(username,password);
        accounts.add(insert);
        System.out.println("Attendant added!");
    }

    // returns true if account is added
    public void insertManager(String username, String password) {
        UserManager insert = new UserManager(username,password);
        accounts.add(insert);
        System.out.println("Manager added!");
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
