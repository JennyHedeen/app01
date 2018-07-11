package com.hedeen.john.app01.dao;

import com.hedeen.john.app01.dao.util.ConnectionPool;
import com.hedeen.john.app01.dao.util.DBConnection;
import com.hedeen.john.app01.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminUserDao implements UserDao {
    private static final Logger logger = LogManager.getLogger(AdminUserDao.class);

    private static UserDao userDao = null;

    private AdminUserDao() {}

    public static UserDao getUserDao() {
        if(userDao==null) {
            synchronized (AdminUserDao.class) {
                if(userDao==null) {
                    userDao = new AdminUserDao();
                }
            }
        }
        if(logger.isDebugEnabled()) logger.debug("Admin userDao received");
        return userDao;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        DBConnection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = ConnectionPool.getPool().getConnection();
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM users");
            while(rs.next()) {
                User u = new User(rs.getInt("users.u_id"),
                        rs.getString("users.name"),
                        rs.getString("users.email"),
                        rs.getString("users.password"),
                        User.Role.valueOf(rs.getString("users.role")));
                users.add(u);
                logger.info("User obtained: " + u.toString());
            }
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            if (con != null)
                con.close(st, rs);
        }
        return users;
    }
}
