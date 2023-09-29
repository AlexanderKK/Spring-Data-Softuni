package com.jdbc;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);

//        System.out.print("user: ");
//        String username = scan.nextLine();

//        System.out.print("password: ");
//        String password = scan.nextLine();

        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "impeachment*");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo", properties);

//        Statement statement = connection.createStatement();
//        String query = "SELECT user_name, first_name, last_name FROM users";
        PreparedStatement statement = connection.prepareStatement(
                "SELECT user_name, first_name, last_name, COUNT(ug.game_id) AS games_count " +
                    "FROM users AS u " +
                    "LEFT JOIN users_games AS ug ON u.id = ug.user_id " +
                    "WHERE user_name = ? " +
                    "GROUP BY u.id");

        System.out.print("Enter username: ");
        String username = scan.nextLine();

        statement.setString(1, username);

        ResultSet resultSet = statement.executeQuery();

        StringBuilder sb = new StringBuilder();

        if(resultSet.next()) {
            sb.append("User: ")
                    .append(resultSet.getString("user_name"))
                    .append(System.lineSeparator());

            sb.append(String.format("%s %s has played %d games",
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getInt("games_count")
            ));
        } else {
            sb.append("No such user exists");
        }

        System.out.println(sb);

        connection.close();
    }

}
