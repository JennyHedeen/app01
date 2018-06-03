package com.hedeen.john.app01;

import com.hedeen.john.app01.dao.util.DBHelper;
import com.hedeen.john.app01.model.User;
import com.hedeen.john.app01.service.UserService;
import com.hedeen.john.app01.service.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DBHelper.initDB();

        UserService userService = UserServiceImpl.getUserService();
        for(User user : userService.getUsers())
            System.out.println(user);
    }
}

