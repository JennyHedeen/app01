package com.hedeen.john.app01.dao.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class DBConnection {
    private static final Logger dLogger = LogManager.getLogger(DBConnection.class);
    private Connection connection;

    DBConnection(String driver, String url, String username, String password) throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, username, password);
    }

    public void close(Statement st, ResultSet rs) {
        try {
            if(rs!=null) {
                rs.close();
                if(dLogger.isDebugEnabled()) dLogger.debug("ResultSet closed");
            }
            if(st!=null) {
                st.close();
                if(dLogger.isDebugEnabled()) dLogger.debug("Statement closed");
            }
            ConnectionPool.getPool().close(this);
            if(dLogger.isDebugEnabled()) dLogger.debug("Connection closed");
        } catch (SQLException e) {
            dLogger.error(e);
        }
    }

    public Statement createStatement() throws SQLException {
        return connection.createStatement();
    }
}
