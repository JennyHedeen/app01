package com.hedeen.john.app01.service;

import com.hedeen.john.app01.dao.UserDao;
import com.hedeen.john.app01.dao.UserDaoImpl;
import com.hedeen.john.app01.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private static UserService userService = null;

    public UserServiceImpl() {
        userDao = UserDaoImpl.getUserDao();
    }

    public static UserService getUserService() {
        if(userService==null) {
            synchronized (UserServiceImpl.class) {
                if(userService==null) {
                    userService = new UserServiceImpl();
                }
            }
        }
        return userService;
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }
}
