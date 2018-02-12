package com.vedantu;
import com.vedantu.service.Order_Service;

import java.util.ArrayList;
import java.util.Scanner;

import com.vedantu.models.Item;
import com.vedantu.utils.DB_Init;

public class Main {
    public static void main(String[] args) {
        // Uncomment the below line to create an inventory table
        DB_Init.createTestDB();
        Scanner reader = new Scanner(System.in);
        ArrayList<Item> items = Order_Service.getItems();
        System.out.println("Items Available");
        System.out.println("  Name || Price || Quantity Available");
        for(int i=0; i<items.size(); i++){
            System.out.println("  "+items.get(i).getName()+" || "+items.get(i).getPrice()+" || "+items.get(i).getQuantity());
        }
        System.out.println("");
        System.out.println("****************");
        System.out.println("Enter user id: ");

        String userID = reader.nextLine().trim();


        System.out.println("Enter the item name to order");
        String itemName = reader.nextLine().trim();
        Boolean isValidName = false;
        for(int i=0; i<items.size(); i++) {
            if(items.get(i).getName().equalsIgnoreCase(itemName)){
                isValidName=true;
            }
        }

        if(isValidName==false) {
            System.out.println("Invalid item name");
            return;
        }

        System.out.println("Enter the desired quantity: ");
        int quantity = reader.nextInt();

        Order_Service.createOrder(itemName, quantity, userID);


    }
}
