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

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

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
        if(logger.isDebugEnabled()) logger.debug("userDao received");
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
            rs = st.executeQuery("SELECT users.*, user_roles.role FROM users, user_roles WHERE users.u_id=user_roles.user_id");
            while(rs.next()) {
                User u = new User(rs.getInt("users.u_id"), rs.getString("users.name"), rs.getString("users.email"), rs.getString("users.password"));
                users.add(u);
                logger.info("User created: " + u.toString());
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
