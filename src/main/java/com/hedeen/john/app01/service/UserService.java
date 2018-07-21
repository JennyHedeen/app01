package com.hedeen.john.app01.service;

import com.hedeen.john.app01.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    User create(User user);

    void delete(int id);

    User get(int id);

    User getByEmail(String email);

    void update(User user);

    List<User> getAll();

    List<User> getAllMasters();
}
