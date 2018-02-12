package com.vedantu.models;

public class Item {

    private int quantity;
    private float price;
    private String name;

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public Item(int quantity, float price, String name) {
        this.quantity = quantity;
        this.price = price;
        this.name = name;
    }


}
