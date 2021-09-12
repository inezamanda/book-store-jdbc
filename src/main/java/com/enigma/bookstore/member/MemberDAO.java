package com.enigma.bookstore.member;

import com.enigma.bookstore.config.DBConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
    DBConnector dbConnector = new DBConnector();
//    Connection connection = dbConnector.connect();

    public void insertMember (String firstName, String lastName, String email, String username, String password) throws SQLException {
        Connection connection = dbConnector.connect();
        String query = "INSERT INTO member (first_name, last_name, email, username, password) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        try {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        preparedStatement.close();
        connection.close();
    }

    public void getMemberById(Integer memberId) throws SQLException {
        Connection connection = dbConnector.connect();
        String query = "SELECT * FROM member WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, memberId);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            String username = resultSet.getString("username");
            System.out.printf("%d. %s, %s, %s, %s\n", memberId, firstName, lastName, email, username);
        } else {
            System.out.println("Data is not found");
        }

        preparedStatement.close();
        connection.close();
    }

    public List<String> getAllMember() throws SQLException {
        Connection connection = dbConnector.connect();
        String query = "SELECT * FROM member";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();

        List<String> members = new ArrayList<>();
        while (resultSet.next()) {
            Integer memberId = resultSet.getInt("id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            String username = resultSet.getString("username");
            members.add(String.format("%d. %s, %s, %s, %s\n", memberId, firstName, lastName, email, username));
        }

        preparedStatement.close();
        connection.close();

        return  members;
    }

    public void updateMemberById(Integer id, String firstName) throws SQLException {
        Connection connection = dbConnector.connect();
        String query = "UPDATE member SET first_name = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        try {
            preparedStatement.setString(1, firstName);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            System.out.println("Succeed update data");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        preparedStatement.close();
        connection.close();
    }

    public void deleteMemberById (Integer memberId) throws SQLException {
        Connection connection = dbConnector.connect();
        String query = "DELETE FROM member WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        try {
            preparedStatement.setInt(1, memberId);
            preparedStatement.executeUpdate();
            System.out.println("Succeed delete data");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        preparedStatement.close();
        connection.close();
    }

//    public void updateMemberByUsername (String username, String firstName, String lastName) throws SQLException {
//        String query = "UPDATE member SET first_name = ?, last_name = ? WHERE username = ?";
//        PreparedStatement preparedStatement = connection.prepareStatement(query);
//
//        try {
//            preparedStatement.setString(1, firstName);
//            preparedStatement.setString(2, lastName);
//            preparedStatement.setString(3, username);
//            preparedStatement.executeUpdate();
//            System.out.println("Succeed update data");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//        preparedStatement.close();
//        connection.close();
//    }
//
//    public void updateMemberByFirstName (String stringName, String lastName) throws SQLException {
//        String query = "UPDATE member SET last_name = ? WHERE first_name LIKE ?";
//        PreparedStatement preparedStatement = connection.prepareStatement(query);
//
//        try {
//            preparedStatement.setString(1, lastName);
//            preparedStatement.setString(2, "%" + stringName + "%");
//            preparedStatement.executeUpdate();
//            System.out.println("Succeed update data");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//        preparedStatement.close();
//        connection.close();
//    }

//    public void deleteMemberByUsername (String username) throws SQLException {
//        String query = "DELETE FROM member WHERE username = ?";
//        PreparedStatement preparedStatement = connection.prepareStatement(query);
//
//        try {
//            preparedStatement.setString(1, username);
//            preparedStatement.executeUpdate();
//            System.out.println("Succeed delete data");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//        preparedStatement.close();
//        connection.close();
//    }
}
