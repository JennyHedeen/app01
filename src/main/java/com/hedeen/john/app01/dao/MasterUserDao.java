package com.hedeen.john.app01.dao;

import com.hedeen.john.app01.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

public class MasterUserDao implements UserDao {
    private static final Logger logger = LogManager.getLogger(MasterUserDao.class);

    private static UserDao userDao = null;

    private MasterUserDao() {}

    public static UserDao getUserDao() {
        if(userDao==null) {
            synchronized (MasterUserDao.class) {
                if(userDao==null) {
                    userDao = new MasterUserDao();
                }
            }
        }
        if(logger.isDebugEnabled()) logger.debug("Master userDao received");
        return userDao;
    }

    @Override
    public List<User> getUsers() {
        return Collections.emptyList();
    }
}
