package com.hedeen.john.app01.dao;

import com.hedeen.john.app01.model.User.Role;

public class UserDaoFactory {

    public static UserDao getUserDao(Role role) {
        switch (role) {
            case CLIENT:
                return ClientUserDao.getUserDao();
            case MASTER:
                return MasterUserDao.getUserDao();
            case ADMIN:
                return AdminUserDao.getUserDao();
            default:
                return DefaultUserDao.getUserDao();
        }
    }
}
