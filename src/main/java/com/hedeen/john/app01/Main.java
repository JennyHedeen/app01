package com.hedeen.john.app01;

import com.hedeen.john.app01.dao.util.DBHelper;
import com.hedeen.john.app01.model.User;
import com.hedeen.john.app01.service.UserService;
import com.hedeen.john.app01.service.UserServiceImpl;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DBHelper.initDB();

        UserService userService = UserServiceImpl.getUserService();
        List<User> users = userService.getUsers();
        for(User user : users)
            System.out.println(user);
    }
}

