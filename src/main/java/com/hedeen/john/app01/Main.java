package com.hedeen.john.app01;

import com.hedeen.john.app01.dao.util.DBConnection;
import com.hedeen.john.app01.dao.util.DBHelper;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DBHelper.initDB();
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT users.*, user_roles.role FROM users, user_roles WHERE users.u_id=user_roles.user_id");
        DBHelper.showResultSet(rs);
    }
}

