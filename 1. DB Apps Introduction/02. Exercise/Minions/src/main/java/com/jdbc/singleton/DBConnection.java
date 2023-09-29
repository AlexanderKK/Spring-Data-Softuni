package com.jdbc.singleton;

import com.jdbc.enums.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static Connection connection;

    private DBConnection() {
    }

    public static Connection getConnection() throws SQLException {
        if(connection == null) {
            final Properties properties = new Properties();

            properties.setProperty(Constants.USER_KEY, Constants.USER_VALUE);
            properties.setProperty(Constants.PASSWORD_KEY, Constants.PASSWORD_VALUE);

            connection = DriverManager.getConnection(Constants.JDBC_URL, properties);
        }

        return connection;
    }

}
