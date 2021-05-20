package com.ankitthakur.creational.singleton;

import org.apache.derby.jdbc.EmbeddedDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbSingleton {
    // Lazy loading of the instance. Helps in improving the performance.
    // Thread safe due to volatile.
    private static volatile DbSingleton instance = null;
    private static volatile Connection connection = null;

    private DbSingleton() {
        try {
            DriverManager.registerDriver(new EmbeddedDriver());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (connection != null) {
            throw new RuntimeException("Use getConnection() method to create");
        }

        // To avoid instance creation via reflection.
        if (instance != null) {
            throw new RuntimeException("Use getInstance() method to create");
        }
    }

    public static DbSingleton getInstance() {
        if (instance == null) {
            // synchronized will help prevent race condition when two threads check for
            // instance being null.
            synchronized (DbSingleton.class) {
                if (instance == null) {
                    instance = new DbSingleton();
                }
            }
        }
        return instance;
    }

    // Instantiate after getting the object of the DbSingleton class
    public Connection getConnection() {
        if (connection == null) {
            synchronized (DbSingleton.class) {
                if (connection == null) {
                    try {
                        String dbUrl = "jdbc:derby:memory:codejava/webdb;create=true";
                        connection = DriverManager.getConnection(dbUrl);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        }
        return connection;
    }
}
