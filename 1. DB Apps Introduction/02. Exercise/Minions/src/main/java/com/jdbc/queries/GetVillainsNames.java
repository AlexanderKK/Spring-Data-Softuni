package com.jdbc.queries;

import com.jdbc.enums.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetVillainsNames {
    private static final String COLUMN_LABEL_MINIONS_COUNT = "minions_count";
    private static final String PRINT_FORMAT = "%s %d%n";
    private static final String GET_VILLAINS_NAMES =
            "SELECT v.`name` AS `name`, COUNT(DISTINCT mv.minion_id) AS minions_count " +
            "FROM villains AS v " +
            "JOIN minions_villains AS mv on v.id = mv.villain_id " +
            "GROUP BY v.id, `name` " +
            "HAVING minions_count > ? " +
            "ORDER BY minions_count DESC";

    public static void query(Connection connection) throws SQLException {
        final PreparedStatement statement = connection.prepareStatement(GET_VILLAINS_NAMES);

        statement.setInt(1, 15);

        final ResultSet result = statement.executeQuery();

        while (result.next()) {
            final String villainName = result.getString(Constants.COLUMN_LABEL_NAME);
            final int minionsCount = result.getInt(COLUMN_LABEL_MINIONS_COUNT);

            System.out.printf(PRINT_FORMAT, villainName, minionsCount);
        }
    }
}
