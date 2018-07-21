package com.hedeen.john.app01.dao;

import com.hedeen.john.app01.dao.util.ConnectionPool;
import com.hedeen.john.app01.dao.util.DBConnection;
import com.hedeen.john.app01.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
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
        try(DBConnection con = ConnectionPool.getPool().getConnection()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM users");
            while(rs.next()) {
                User u = new User(rs.getInt("users.u_id"),
                        rs.getString("users.name"),
                        rs.getString("users.email"),
                        rs.getString("users.password"),
                        User.Role.valueOf(rs.getString("users.role")));
                users.add(u);
                logger.info("User obtained: " + u.toString());
            }
            rs.close();
        } catch (SQLException e) {
            logger.error(e);
        }
        return users;
    }

    @Override
    public User save(User user) {
        try(DBConnection con = ConnectionPool.getPool().getConnection()) {
            PreparedStatement pst;
            if(user.isNew()) {
                String query = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";
                pst = con.prepareInsertStatement(query, user.getName(), user.getEmail(), user.getPassword(), User.Role.CLIENT.toString());
                if(pst.executeUpdate()==1) {
                    ResultSet rs = pst.getGeneratedKeys();
                    rs.next();
                    user.setId(rs.getInt(1));
                    rs.close();
                }
                logger.info("New user created with id={}", user.getId());
            } else {
                if(user.getPassword().isEmpty()) {
                    String query = "UPDATE users SET name=?, email=?, role=? WHERE u_id=?";
                    pst = con.prepareStatement(query, user.getName(), user.getEmail(), user.getRole().toString(), user.getId());
                } else {
                    String query = "UPDATE users SET name=?, email=?, password=? WHERE u_id=?";
                    pst = con.prepareStatement(query, user.getName(), user.getEmail(), user.getPassword(), user.getRole().toString(), user.getId());
                }

                if(pst.executeUpdate()!=1) {
                    logger.error("User was not not created");
                    // TODO: throw new exception
                }
                logger.info("User data successfully updated for userId=" + user.getId());
            }
        } catch (SQLException e) {
            if(e.getMessage().contains("Duplicate")) {
                logger.error(e);
//                throw new DuplicateEntityException();
            }
            logger.error(e);
//            throw new DBException("Unable to save new record");
        }
        return user;
    }

    @Override
    public boolean delete(int id) {
        int resultRows = 0;
        try(DBConnection con = ConnectionPool.getPool().getConnection()) {
            String query = "DELETE FROM users WHERE u_id=?;";
            PreparedStatement pst = con.prepareStatement(query, id);
            resultRows = pst.executeUpdate();
            logger.info("Deleted record id={}", id);
        } catch (SQLException e) {
            logger.error(e.getMessage());
//            throw new DBException("Unable to delete record");
        }
        return resultRows == 1;
    }

    @Override
    public User getByEmail(String email) {
        String query = "SELECT * FROM users WHERE email=?";
        return getUser(query, email);
    }

    @Override
    public User getById(int id) {
        String query = "SELECT * FROM users WHERE u_id=?";
        return getUser(query, id);
    }

    @Override
    public List<User> getAllMasters() {
        List<User> allMasters = new ArrayList<>();

        try(DBConnection con = ConnectionPool.getPool().getConnection()) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM users WHERE role='MASTER' ORDER BY u_id");
            while(rs.next()) {
                allMasters.add(new User(rs.getInt("u_id"), rs.getString("name"), rs.getString("email"),
                        rs.getString("password"), User.Role.valueOf(rs.getString("role"))));
            }
            rs.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
//            throw new DBException("Unable to obtain records");
        }
        return allMasters;
    }

    private User getUser(String query, Object... values) {
        User user = null;

        try(DBConnection con = ConnectionPool.getPool().getConnection()) {
            PreparedStatement pst = con.prepareStatement(query, values);
            ResultSet rs = pst.executeQuery();

            if(rs.next()) {
                user = new User(rs.getInt("u_id"), rs.getString("name"),
                        rs.getString("email"), rs.getString("password"),
                        User.Role.valueOf(rs.getString("role")));
            }
            rs.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
//            throw new DBException("Unable to obtain record");
        }
        return user;
    }

}
