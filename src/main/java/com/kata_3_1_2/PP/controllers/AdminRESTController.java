package com.kata_3_1_2.PP.controllers;

import com.kata_3_1_2.PP.entitys.User;
import com.kata_3_1_2.PP.exception_handling.NoSuchUserException;
import com.kata_3_1_2.PP.exception_handling.UserIncorrectData;
import com.kata_3_1_2.PP.service.RoleService;
import com.kata_3_1_2.PP.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRESTController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public AdminRESTController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/users")
    public List<User> printUsers() {
        List<User> allUsers = userService.listUsers();
        return allUsers;
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new NoSuchUserException("There is no user with ID " + id + " in DB");
        }
        return user;
    }

    @PostMapping("/users")
    public User addNewUser(@RequestBody User user) {
        userService.addUser(user);
        return user;
    }

    @PutMapping("/users")
    public User editUser(@RequestBody User user) {
        userService.updateUser(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new NoSuchUserException("There is no user with ID " + id + " in DB");
        }
        userService.removeUser(id);
        return "User with id " + id + " was deleted.";
    }
}
