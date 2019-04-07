package com.example.addnewinventoryitem;


import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {


String date;
ArrayList<Inventory> inventory = new ArrayList<Inventory>();




    public Order(String _date, Inventory items) {

        date = _date;

        inventory.add(items);




    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Inventory> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Inventory> inventory) {
        this.inventory = inventory;
    }



}
