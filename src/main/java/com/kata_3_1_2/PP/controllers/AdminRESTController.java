package com.kata_3_1_2.PP.controllers;

import com.kata_3_1_2.PP.entitys.User;
import com.kata_3_1_2.PP.exception_handling.NoSuchUserException;
import com.kata_3_1_2.PP.exception_handling.UserIncorrectData;
import com.kata_3_1_2.PP.service.RoleService;
import com.kata_3_1_2.PP.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
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
    public ResponseEntity<List<User>> printUsers() {
        List<User> allUsers = userService.listUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
/*@GetMapping("/users")
public List<User> printUsers(Principal principal, Model model) {
    model.addAttribute("users",userService.listUsers());
    model.addAttribute("roles", roleService.getAll());
    model.addAttribute("user", new User());
    model.addAttribute("currentUser", userService.getByName(principal.getName()));
    return "users-list";
}*/

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
