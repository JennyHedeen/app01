package com.hedeen.john.app01.dao.util;

import java.sql.*;
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

    public static void close(Connection connection, Statement st, ResultSet rs) {
        try {
            if(rs!=null) rs.close();
            if(st!=null) st.close();
            if(connection!=null) connection.close();
        } catch (SQLException e) {
//            logger.error(e.getMessage());
        }
    }
}
