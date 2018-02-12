package com.vedantu.utils;

import java.sql.*;

public class DbConnection {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/vedantu";

    static final String USER = "root";
    static final String PASS = "qwerty123";

    static Connection connection = null;

    public static Connection getConnection() {
        if(connection!=null){
            return connection;
        }
        else{
            createConnection();
            return connection;
        }
    }

    private static void createConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

        }catch (SQLException se){
            se.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }



}
