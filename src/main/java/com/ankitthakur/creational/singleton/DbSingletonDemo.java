package com.ankitthakur.creational.singleton;

import com.ankitthakur.creational.singleton.DbSingleton;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DbSingletonDemo {
    public static void main(String[] args) {
        DbSingleton instance = DbSingleton.getInstance();

        long timeBefore = 0;
        long timeAfter = 0;

        timeBefore = System.currentTimeMillis();
        Connection connection = instance.getConnection();
        timeAfter = System.currentTimeMillis();

        System.out.println(timeAfter - timeBefore);

        Statement statement;
        try {
            statement = connection.createStatement();
            int count = statement.executeUpdate(
                    "CREATE TABLE Address (ID INT, StreetName VARCHAR(20)," +
                            " City VARCHAR(20))");
            System.out.println("Table created.");
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        timeBefore = System.currentTimeMillis();
        connection = instance.getConnection();
        timeAfter = System.currentTimeMillis();

        System.out.println(timeAfter - timeBefore);
    }
}
