package com.jdbc.queries;

import com.jdbc.enums.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChangeTownNamesCasing {

    private static final String PRINT_TOWNS_AFFECTED = "%d town names were affected.%n%s%n";
    private static final String PRINT_TOWN_AFFECTED = "%d town name was affected.%n%s%n";
    private static final String PRINT_NO_TOWNS_AFFECTED = "No town names were affected.%n";
    private static final String SET_TOWN_NAMES_TO_UPPERCASE_BY_COUNTRY =
            "UPDATE towns " +
            "SET `name` = UPPER(`name`) " +
            "WHERE country LIKE ?";

    private static final String GET_UPDATED_TOWN_NAMES =
            "SELECT `name` FROM towns " +
            "WHERE country LIKE ?";

    public static void query(Connection connection, String country_name) throws SQLException {
        final PreparedStatement statementUpdateTowns = connection
                .prepareStatement(SET_TOWN_NAMES_TO_UPPERCASE_BY_COUNTRY);
        statementUpdateTowns.setString(1, country_name);

        int resultRow = statementUpdateTowns.executeUpdate();

        List<String> townNames = new ArrayList<>();
        if(resultRow != 0) {
            final PreparedStatement statementGetTowns = connection.prepareStatement(GET_UPDATED_TOWN_NAMES);
            statementGetTowns.setString(1, country_name);

            final ResultSet resultTowns = statementGetTowns.executeQuery();

            while(resultTowns.next()) {
                townNames.add(resultTowns.getString(Constants.COLUMN_LABEL_NAME));
            }

            String printFormat = townNames.size() == 1 ? PRINT_TOWN_AFFECTED : PRINT_TOWNS_AFFECTED;
            System.out.printf(printFormat, townNames.size(), townNames);
        } else {
            System.out.printf(PRINT_NO_TOWNS_AFFECTED);
        }
    }

}
