package com.example.atm;

public class UserInfo {
    private String username;
    private String password;
    private String pin;
    private String name;
    private int balance;
    public UserInfo(){

    }
    public UserInfo(String username, String password, String pin, String name, int balance) {
        this.username = username;
        this.password = password;
        this.pin = pin;
        this.name = name;
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
