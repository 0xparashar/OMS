package com.vedantu.service;

import com.vedantu.models.Item;
import com.vedantu.models.Order;
import com.vedantu.utils.DbConnection;
import java.sql.*;
import java.util.ArrayList;

public class Order_Service {


    public static ArrayList<Item> getItems() {
        Connection connection = DbConnection.getConnection();
        Statement stmt=null;
        ArrayList<Item> result = new ArrayList<Item>();
        try{
            stmt = connection.createStatement();
            String sql = "SELECT * FROM inventory";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                result.add(new Item(rs.getInt("quantity"),rs.getFloat("price"), rs.getString("name") ));
            }
        }catch (SQLException se) {
            se.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private static Item updateInventory(Connection connection, String itemName, int quantity){
        Statement stmt=null;
        Item item=null;
        try{
            stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            String query = "SELECT * FROM inventory WHERE name=\""+itemName+"\" AND quantity >= "+quantity+" FOR UPDATE";
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next() == false){
                throw new SQLException("Either quantity is more than available or item doesn't exist");
            }else{
                do{
                    item = new Item(rs.getInt("quantity"),rs.getFloat("price"), itemName);
                    rs.updateInt("quantity", item.getQuantity()-quantity);
                    rs.updateRow();
                    item.setQuantity(item.getQuantity()-quantity);
                }while(rs.next());
            }
        }catch (SQLException se) {
            se.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return item;

    }




    public static void createOrder(String itemName, int quantity, String userID){
        Connection connection = DbConnection.getConnection();
        Statement stmt=null;
        ArrayList<Item> result = new ArrayList<Item>();
        Order order;
        try{
            connection.setAutoCommit(false);
            Item item;

            item = updateInventory(connection, itemName, quantity);
            String query = "INSERT INTO orders(user_id, item_name, amount, quantity, status) VALUES(\""+userID+"\",\""+itemName+"\","+quantity*item.getPrice()+","+quantity+",\"PENDING\")";

            PreparedStatement statement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

            int count = statement.executeUpdate();

            if(count==0){
                throw new SQLException("Error creating order");
            }

            try(ResultSet generated_keys = statement.getGeneratedKeys()) {
                if(generated_keys.next()){
                    order = new Order(generated_keys.getInt(1), userID, item, quantity, "PENDING");
                }else{
                    throw new SQLException("Error creating order");
                }
            }


            connection.commit();
            System.out.println("*******Your order summary*******");
            System.out.println("Order_id: "+order.getOrderID());
            System.out.println("Amount: "+order.getAmount());
            System.out.println("User ID: "+order.getUserID());
            System.out.println("Item: "+order.getItem().getName());
            System.out.println("Quantity: "+order.getQuantity());

        }catch (SQLException se) {
            se.printStackTrace();
            try {
                connection.rollback();
            }catch (Exception e){
                e.printStackTrace();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


}
