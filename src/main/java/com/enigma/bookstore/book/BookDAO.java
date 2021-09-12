package com.enigma.bookstore.book;

import com.enigma.bookstore.config.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    DBConnector dbConnector = new DBConnector();

    public void insertBook (String title, String description,
                            String publisher, Integer year, Integer page,
                            String language, Integer stock, Integer price) throws SQLException {

        Connection connection = dbConnector.connect();
        String query = "INSERT INTO book (title, description, publisher, year, page, language, stock, price) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        try {
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, publisher);
            preparedStatement.setInt(4, year);
            preparedStatement.setInt(5, page);
            preparedStatement.setString(6, language);
            preparedStatement.setInt(7, stock);
            preparedStatement.setInt(8, price);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        preparedStatement.close();
        connection.close();
    }

    public void getBookById(Integer bookId) throws SQLException {
        Connection connection = dbConnector.connect();
        String query = "SELECT * FROM book WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, bookId);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            String publisher = resultSet.getString("publisher");
            Integer year = resultSet.getInt("year");
            Integer page = resultSet.getInt("page");
            String language = resultSet.getString("language");
            Integer stock = resultSet.getInt("stock");
            Integer price = resultSet.getInt("price");
            System.out.printf("%d. %s, %s, %s, %d, %d, %s, %d, %d\n",
                    bookId, title, description, publisher, year, page, language, stock, price);
        } else {
            System.out.printf("Data is not found");
        }
        preparedStatement.close();
        connection.close();
    }

    public List<String> getAllBook() throws SQLException {
        Connection connection = dbConnector.connect();
        String query = "SELECT * FROM book";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<String> books = new ArrayList<>();
        while (resultSet.next()) {
            Integer bookId = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            String publisher = resultSet.getString("publisher");
            Integer year = resultSet.getInt("year");
            Integer page = resultSet.getInt("page");
            String language = resultSet.getString("language");
            Integer stock = resultSet.getInt("stock");
            Integer price = resultSet.getInt("price");
            books.add(String.format("%d. %s, %s, %s, %d, %d, %s, %d, %d\n",
                    bookId, title, description, publisher, year, page, language, stock, price));
        }

        preparedStatement.close();
        connection.close();

        return books;
    }

    public void updateBookById(Integer id, String title) throws SQLException {
        Connection connection = dbConnector.connect();
        String query = "UPDATE book SET title = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        try {
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        preparedStatement.close();
        connection.close();
    }

    public void deleteBookById (Integer bookId) throws SQLException {
        Connection connection = dbConnector.connect();
        String query = "DELETE FROM book WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        try {
            preparedStatement.setInt(1, bookId);
            preparedStatement.executeUpdate();
            System.out.println("Succeed delete data");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        preparedStatement.close();
        connection.close();
    }
}
