package com.kata_3_1_2.PP.service;

import com.kata_3_1_2.PP.entitys.User;
import java.util.List;

public interface UserService {
    void addUser(User user, String[] roles);
    void removeUser(Long id);
    User getUserById(Long id);
    List<User> listUsers();
    void updateUser(User user, String[] roles);
    User getByName(String name);
}
