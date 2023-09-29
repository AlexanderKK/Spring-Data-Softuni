package com.jdbc.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddMinionToVillain {

    private static final String GET_TOWN_BY_NAME =
            "SELECT id FROM towns " +
            "WHERE `name` = ?";
    private static final String GET_VILLAIN_BY_NAME =
            "SELECT id FROM villains " +
                    "WHERE `name` = ?";
    private static final String GET_MINION_BY_NAME =
            "SELECT id FROM minions " +
                    "WHERE `name` = ?";
    private static final String INSERT_TOWN =
            "INSERT INTO towns (`name`) " +
            "VALUES (?)";
    private static final String INSERT_VILLAIN =
            "INSERT INTO villains (`name`, `evilness_factor`) " +
            "VALUES (?, 'evil')";
    private static final String INSERT_MINION =
            "INSERT INTO minions (`name`, `age`, `town_id`) " +
            "VALUES (?, ?, ?)";
    private static final String INSERT_MINION_TO_VILLAIN =
            "INSERT INTO minions_villains (`minion_id`, `villain_id`) " +
            "VALUES (?, ?)";
    private static final String PRINT_TOWN_ADDED = "Town %s was added to the database.%n";
    private static final String PRINT_VILLAIN_ADDED = "Villain %s was added to the database.%n";
    private static final String PRINT_MINION_ADDED = "Minion %s was added to the database.%n";
    private static final String PRINT_MINION_ADDED_TO_VILLAIN = "Successfully added %s to be minion of %s.%n";

    public static void query(Connection connection, String minionName, int minionAge, String townName, String villainName) throws SQLException {
        boolean townInserted = insertTown(connection, townName) == 1;
        if(townInserted) {
            System.out.printf(PRINT_TOWN_ADDED, townName);
        }

        boolean villainInserted = insertVillain(connection, villainName) == 1;
        if(villainInserted) {
            System.out.printf(PRINT_VILLAIN_ADDED, villainName);
        }

        int townId = getIdByName(connection, GET_TOWN_BY_NAME, townName);
        
        boolean minionInserted = insertMinion(connection, minionName, minionAge, townId) == 1;
        if(minionInserted) {
            System.out.printf(PRINT_MINION_ADDED, minionName);
        }

        int minionId = getIdByName(connection, GET_MINION_BY_NAME, minionName);
        int villainId = getIdByName(connection, GET_VILLAIN_BY_NAME, villainName);
        
        boolean minionAddedToVillain = addMinionToVillain(connection, minionId, villainId) == 1;
        if(minionAddedToVillain) {
            System.out.printf(PRINT_MINION_ADDED_TO_VILLAIN, minionName, villainName);
        }
    }

    private static int addMinionToVillain(Connection connection, int minionId, int villainId) throws SQLException {
        final PreparedStatement stmt = connection.prepareStatement(INSERT_MINION_TO_VILLAIN);
        stmt.setInt(1, minionId);
        stmt.setInt(2, villainId);

        return stmt.executeUpdate();
    }

    private static int insertMinion(Connection connection, String minionName, int minionAge, int townId) throws SQLException {
        int idByMinionName = getIdByName(connection, GET_MINION_BY_NAME, minionName);
        if(idByMinionName == 0) {
            final PreparedStatement stmt = connection.prepareStatement(INSERT_MINION);
            stmt.setString(1, minionName);
            stmt.setInt(2, minionAge);
            stmt.setInt(3, townId);

            return stmt.executeUpdate();
        }

        return 0;
    }

    private static int insertVillain(Connection connection, String villainName) throws SQLException {
        int idByVillainName = getIdByName(connection, GET_VILLAIN_BY_NAME, villainName);
        if(idByVillainName == 0) {
            final PreparedStatement stmt = connection.prepareStatement(INSERT_VILLAIN);
            stmt.setString(1, villainName);

            return stmt.executeUpdate();
        }

        return 0;
    }

    private static int insertTown(Connection connection, String townName) throws SQLException {
        int idByTownName = getIdByName(connection, GET_TOWN_BY_NAME, townName);
        if(idByTownName == 0) {
            final PreparedStatement stmt = connection.prepareStatement(INSERT_TOWN);
            stmt.setString(1, townName);

            return stmt.executeUpdate();
        }

        return 0;
    }

    private static int getIdByName(Connection connection, String query, String name) throws SQLException {
        final PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, name);

        ResultSet existingRecord = stmt.executeQuery();

        if(existingRecord.next()) {
            return existingRecord.getInt("id");
        }

        return 0;
    }

}
