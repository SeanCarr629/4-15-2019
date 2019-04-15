package com.example.addnewinventoryitem;


import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {


String date;
ArrayList<Inventory> inventory;
Inventory items;


public Order ()
{
    this.date = "0";
    this.inventory = new ArrayList<Inventory>();

            }



    public Order(String _date, Inventory items) {

        date = _date;
        this.inventory = new ArrayList<Inventory>();
        inventory.add(items);




    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Inventory> getInventory() {
        return this.inventory;
    }

    public void setInventory(ArrayList<Inventory> inventory) {
        this.inventory = inventory;
    }

    public void addItem (Inventory item)
    {

        inventory.add(item);


    }

    public String getItemName(int j) {

       return this.inventory.get(j).getItemName();

    }

}
