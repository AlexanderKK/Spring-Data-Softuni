package com.jdbc;

import com.jdbc.queries.GetAllMinionNamesFirstLast;
import com.jdbc.queries.IncreaseMinionAgeUsingProcedure;
import com.jdbc.singleton.DBConnection;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        final Connection connection = DBConnection.getConnection(); //Object with only one instance (Singleton)
//        final Connection connection = Utils.getSQLConnection(); (Enum Utils)

        //1
//        GetVillainsNames.query(connection);

//        2
//        System.out.print("Enter Villain ID: ");
//        int villain_id = Integer.parseInt(scanner.nextLine());
//
//        GetMinionNames.query(connection, villain_id);

        //3
//        System.out.print("Minion: ");
//
//        String[] minionData = scanner.nextLine().split("\\s+");
//        String minionName = minionData[0];
//        int minionAge = Integer.parseInt(minionData[1]);
//        String townName = minionData[2];
//
//        System.out.print("Villain: ");
//
//        String villainName = scanner.nextLine();
//
//        AddMinionToVillain.query(connection, minionName, minionAge, townName, villainName);
//
//        //4
//        System.out.print("Enter country name: ");
//        String countryName = scanner.nextLine();
//
//        ChangeTownNamesCasing.query(connection, countryName);

        //5
//        GetAllMinionNamesFirstLast.query(connection);

        //6
//        IncreaseMinionAgeUsingProcedure.query(connection, minionId);

    }
}
