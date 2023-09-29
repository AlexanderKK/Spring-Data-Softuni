package com.jdbc.queries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GetAllMinionNamesFirstLast {

    private static final String GET_MINION_NAMES = "SELECT `name` FROM minions";

    public static void query(Connection connection) throws SQLException {
        List<String> minionNames = new ArrayList<>();

        PreparedStatement stmt = connection.prepareStatement(GET_MINION_NAMES);

        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            minionNames.add(rs.getString("name"));
        }

        int halfSize = minionNames.size() / 2;
        for (int i = 0; i < halfSize; i++) {
            System.out.println(minionNames.get(i));
            System.out.println(minionNames.get(minionNames.size() - 1 - i));
        }

        if(minionNames.size() % 2 != 0) {
            System.out.println(minionNames.get(halfSize));
        }
    }

}
