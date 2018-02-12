package com.vedantu.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.vedantu.utils.DbConnection;

public class DB_Init {



    public static void createTestDB() {
        Connection connection = DbConnection.getConnection();
        try{
            Statement stmt = connection.createStatement();
            String inventoryCreator = "CREATE TABLE inventory (name varchar(255) NOT NULL PRIMARY KEY, price float, quantity int);";
            String ordersCreator =  "CREATE TABLE orders( order_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, user_id varchar(255), item_name varchar(255), amount float, quantity int, createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP, status varchar(255))";
            stmt.execute(inventoryCreator);
            stmt.execute(ordersCreator);
            String query = "INSERT INTO inventory(name, price, quantity) VALUES (\"Eraser\",7.5, 500),(\"Pencil\",15, 100),(\"Sharpener\", 2.5, 1000)";
            stmt.execute(query);

        }catch (SQLException se) {
            se.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
