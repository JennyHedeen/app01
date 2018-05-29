package com.hedeen.john.app01.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnection {

    private static Connection connection;

    private DBConnection() {}

    public static Connection getConnection() {
        if(connection == null) {
            synchronized (DBConnection.class) {
                if(connection == null) {
                    try {
                        ResourceBundle appRB = ResourceBundle.getBundle("application");
                        ResourceBundle dbRB = ResourceBundle.getBundle(appRB.getString("app.db"));
                        Class.forName(dbRB.getString("database.driver"));
                        connection = DriverManager.getConnection(
                                dbRB.getString("database.url"),
                                dbRB.getString("database.user"),
                                dbRB.getString("database.password"));
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return connection;
    }
}
