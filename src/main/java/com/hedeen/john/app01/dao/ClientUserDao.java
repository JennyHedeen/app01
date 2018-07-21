package com.hedeen.john.app01.dao;

import com.hedeen.john.app01.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

public class ClientUserDao implements UserDao {
    private static final Logger logger = LogManager.getLogger(ClientUserDao.class);

    private static UserDao userDao = null;

    private ClientUserDao() {}

    public static UserDao getUserDao() {
        if(userDao==null) {
            synchronized (ClientUserDao.class) {
                if(userDao==null) {
                    userDao = new ClientUserDao();
                }
            }
        }
        if(logger.isDebugEnabled()) logger.debug("Client userDao received");
        return userDao;
    }

    @Override
    public List<User> getUsers() {
        return Collections.emptyList();
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        //TODO
        return false;
    }

    @Override
    public User getByEmail(String email) {
        return null;
    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public List<User> getAllMasters() {
        return null;
    }
}
