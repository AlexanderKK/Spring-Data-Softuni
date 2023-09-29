package com.jdbc.queries;

import com.jdbc.enums.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetMinionNames {

    private static final String COLUMN_LABEL_MINION_AGE = "age";
    private static final String PRINT_VILLAIN_FORMAT = "Villain: %s%n";
    private static final String PRINT_MINIONS_FORMAT = "%d. %s %d%n";
    private static final String PRINT_INVALID_VILLAIN = "No villain with ID %d exists in the database.%n";
    private static final String GET_VILLAIN_NAME_BY_ID =
            "SELECT `name` " +
            "FROM villains " +
            "WHERE id = ?";
    private static final String GET_MINIONS_NAMES =
            "SELECT m.`name` AS `name`, m.age " +
            "FROM minions AS m " +
            "JOIN minions_villains AS mv on m.id = mv.minion_id " +
            "WHERE mv.villain_id = ?";

    public static void query(Connection connection, int villain_id) throws SQLException {
        final PreparedStatement statementVillains = connection.prepareStatement(GET_VILLAIN_NAME_BY_ID);
        statementVillains.setInt(1, villain_id);

        final ResultSet resultVillains = statementVillains.executeQuery();

        if (!resultVillains.next()) {
            System.out.printf(PRINT_INVALID_VILLAIN, villain_id);
        } else {
            final String villainName = resultVillains.getString(Constants.COLUMN_LABEL_NAME);
            System.out.printf(PRINT_VILLAIN_FORMAT, villainName);

            final PreparedStatement statementMinions = connection.prepareStatement(GET_MINIONS_NAMES);
            statementMinions.setInt(1, villain_id);

            final ResultSet resultMinions = statementMinions.executeQuery();

            for (int index = 1; resultMinions.next(); index++) {
                final String minionName = resultMinions.getString(Constants.COLUMN_LABEL_NAME);
                final int minionAge = resultMinions.getInt(COLUMN_LABEL_MINION_AGE);

                System.out.printf(PRINT_MINIONS_FORMAT, index, minionName, minionAge);
            }
        }
    }

}
