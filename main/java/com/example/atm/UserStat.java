package com.example.atm;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserStat {
    StringProperty DAT;
    IntegerProperty Amount;
    StringProperty Action;
    IntegerProperty AvlBal;

    public UserStat(String date_time, int amount, String action, int avlBal){
        DAT = new SimpleStringProperty(date_time);
        Amount = new SimpleIntegerProperty(amount);
        Action = new SimpleStringProperty(action);
        AvlBal = new SimpleIntegerProperty(avlBal);
    }

    public String getDAT() {
        return DAT.get();
    }

    public StringProperty DATProperty() {
        return DAT;
    }

    public void setDAT(String DAT) {
        this.DAT.set(DAT);
    }

    public int getAmount() {
        return Amount.get();
    }

    public IntegerProperty amountProperty() {
        return Amount;
    }

    public void setAmount(int amount) {
        this.Amount.set(amount);
    }

    public String getAction() {
        return Action.get();
    }

    public StringProperty actionProperty() {
        return Action;
    }

    public void setAction(String action) {
        this.Action.set(action);
    }

    public int getAvlBal() {
        return AvlBal.get();
    }

    public IntegerProperty avlBalProperty() {
        return AvlBal;
    }

    public void setAvlBal(int avlBal) {
        this.AvlBal.set(avlBal);
    }

}
