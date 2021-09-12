package com.enigma.bookstore.transaction;

import com.enigma.bookstore.config.DBConnector;

import java.sql.*;

public class TransactionDAO {
    public void purchasingBook(Date date, Integer member_id, Integer book_id, Integer qty) throws SQLException {
        DBConnector dbConnector = new DBConnector();
        Connection connection = dbConnector.connect();

        connection.setAutoCommit(false);

        String sqlGetBook = "SELECT price, stock FROM book WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlGetBook);
        preparedStatement.setInt(1, book_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Integer price = resultSet.getInt("price");
        Integer stock = resultSet.getInt("stock");
        preparedStatement.close();

        String sqlInsert = "INSERT INTO transaction (date, member_id, book_id, qty, total) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement insertStatement = connection.prepareStatement(sqlInsert);

        insertStatement.setDate(1, date);
        insertStatement.setInt(2, member_id);
        insertStatement.setInt(3, book_id);
        insertStatement.setInt(4, qty);
        insertStatement.setInt(5, qty*price);
        insertStatement.executeUpdate();
        insertStatement.close();

        String sqlUpdate = "UPDATE book SET stock = ? WHERE id = ?";
        PreparedStatement updateStatement = connection.prepareStatement(sqlUpdate);

        updateStatement.setInt(1, stock - qty);
        updateStatement.setInt(2, book_id);
        updateStatement.executeUpdate();

        connection.commit();
        connection.close();
    }
}
