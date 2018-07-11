package com.hedeen.john.app01.service;

import com.hedeen.john.app01.dao.UserDao;
import com.hedeen.john.app01.dao.UserDaoFactory;
import com.hedeen.john.app01.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private static UserService userService = null;

    private UserServiceImpl(User.Role role) {
        userDao = UserDaoFactory.getUserDao(role);
    }

    public static UserService getUserService(User.Role role) {
        if(userService==null) {
            synchronized (UserServiceImpl.class) {
                if(userService==null) {
                    userService = new UserServiceImpl(role);
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
