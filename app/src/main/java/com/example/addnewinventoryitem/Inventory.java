package com.example.addnewinventoryitem;

import java.io.Serializable;

public class Inventory implements Serializable {

String itemName;
Integer itemQuantity;

    public String getItemName() {
        return itemName;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
