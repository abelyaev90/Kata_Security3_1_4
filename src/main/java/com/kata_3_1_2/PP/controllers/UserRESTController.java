package com.kata_3_1_2.PP.controllers;

import com.kata_3_1_2.PP.entitys.User;
import com.kata_3_1_2.PP.exception_handling.NoSuchUserException;
import com.kata_3_1_2.PP.service.RoleService;
import com.kata_3_1_2.PP.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserRESTController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UserRESTController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new NoSuchUserException("There is no user with ID " + id + " in DB");
        }
        return user;
    }
}
