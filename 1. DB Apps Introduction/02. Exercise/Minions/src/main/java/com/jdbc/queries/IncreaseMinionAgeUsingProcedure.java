package com.jdbc.queries;

import java.sql.*;

public class IncreaseMinionAgeUsingProcedure {

    public static void query(Connection connection, int minionId) throws SQLException {
        CallableStatement stmt = connection.prepareCall("CALL usp_get_older(?)");
        stmt.setInt(1, minionId);

        ResultSet result = stmt.executeQuery();

        while(result.next()) {
            System.out.printf("%s %d%n",
                    result.getString("name"),
                    result.getInt("age"));
        }
    }

}
