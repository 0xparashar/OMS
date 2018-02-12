package com.vedantu.models;

import com.vedantu.models.Item;

public class Order {

    private Item item;
    private int quantity;
    private String userID;
    private float amount;
    private String status;
    private int orderID;

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUserID() {
        return userID;
    }

    public float getAmount() {
        return amount;
    }

    public int getOrderID() {
        return orderID;
    }



    public Order ( int orderID, String userID, Item item, int quantity, String status ) {
        this.quantity = quantity;
        this.item = item;
        this.userID = userID;
        this.amount = quantity*item.getPrice();
        this.orderID = orderID;
        this.status = status;
    }

}
