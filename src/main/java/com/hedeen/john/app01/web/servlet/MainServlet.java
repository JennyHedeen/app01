package com.hedeen.john.app01.web.servlet;

import com.hedeen.john.app01.dao.util.DBConnection;
import com.hedeen.john.app01.dao.util.DBHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        DBHelper.initDB();
        Connection con = DBConnection.getConnection();
        Statement st = null;
        ResultSet rs = null;
        String result = null;
        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT users.*, user_roles.role FROM users, user_roles WHERE users.u_id=user_roles.user_id");
            result = DBHelper.getResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(con, st, rs);
        }
        PrintWriter pw = resp.getWriter();
        pw.write(result);
    }
}
