package com.hedeen.john.app01.dao;

import com.hedeen.john.app01.model.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers();

    User save(User user);

    boolean delete(int id);

    User getByEmail(String email);

    User getById(int id);

    List<User> getAllMasters();
}
