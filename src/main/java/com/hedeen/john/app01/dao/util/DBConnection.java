package com.hedeen.john.app01.dao.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ResourceBundle;

public class DBConnection {
    private static final Logger dLogger = LogManager.getLogger(DBConnection.class);
    private static Connection connection;

    private DBConnection() {}

    public static Connection getConnection() throws SQLException {
        if(connection == null || connection.isClosed()) {
            synchronized (DBConnection.class) {
                if(connection == null || connection.isClosed()) {
                    try {
                        ResourceBundle appRB = ResourceBundle.getBundle("application");
                        ResourceBundle dbRB = ResourceBundle.getBundle(appRB.getString("app.db"));
                        Class.forName(dbRB.getString("database.driver"));
                        connection = DriverManager.getConnection(
                                dbRB.getString("database.url"),
                                dbRB.getString("database.user"),
                                dbRB.getString("database.password"));
                        if(dLogger.isDebugEnabled()) dLogger.debug("new connection created");
                    } catch (SQLException | ClassNotFoundException e) {
                        dLogger.error(e);
                    }
                }
            }
        }
        return connection;
    }

    public static void close(Connection connection, Statement st, ResultSet rs) {
        try {
            if(rs!=null) rs.close();
            if(dLogger.isDebugEnabled()) dLogger.debug("resultSet closed");
            if(st!=null) st.close();
            if(dLogger.isDebugEnabled()) dLogger.debug("statement closed");
            if(connection!=null) connection.close();
            if(dLogger.isDebugEnabled()) dLogger.debug("connection closed");
        } catch (SQLException e) {
            dLogger.error(e);
        }
    }
}
