package com.kata_3_1_2.PP.dao;

import com.kata_3_1_2.PP.entitys.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    void removeUser(Long id);
    User getUserById(Long id);
    List<User> listUsers();
    void updateUser(User user);
    User getByName(String name);
}
