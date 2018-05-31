package com.hedeen.john.app01.dao;

import com.hedeen.john.app01.dao.util.DBConnection;
import com.hedeen.john.app01.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static UserDao userDao = null;

    private UserDaoImpl() {}

    public static UserDao getUserDao() {
        if(userDao==null) {
            synchronized (UserDaoImpl.class) {
                if(userDao==null) {
                    userDao = new UserDaoImpl();
                }
            }
        }
        return userDao;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = DBConnection.getConnection();
            st = con.createStatement();
            rs = st.executeQuery("SELECT users.*, user_roles.role FROM users, user_roles WHERE users.u_id=user_roles.user_id");
            while(rs.next()) {
                users.add(new User(rs.getInt("users.u_id"), rs.getString("users.name"), rs.getString("users.email"), rs.getString("users.password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(con, st, rs);
        }
        return users;
    }
}
