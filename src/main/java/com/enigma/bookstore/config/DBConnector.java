package com.enigma.bookstore.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    public Connection connect() {
        String url = "jdbc:mysql://localhost:3306/enigma_book_store";
        String username = "root";
        String password = "helloworld";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
//            System.out.println("Connected to the MySQL server");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
