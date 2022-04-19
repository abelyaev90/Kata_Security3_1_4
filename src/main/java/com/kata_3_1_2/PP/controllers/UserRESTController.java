package com.kata_3_1_2.PP.controllers;

import com.kata_3_1_2.PP.entitys.User;
import com.kata_3_1_2.PP.exception_handling.NoSuchUserException;
import com.kata_3_1_2.PP.service.RoleService;
import com.kata_3_1_2.PP.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserRESTController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UserRESTController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/users")
    public ResponseEntity<User> getUser(Principal principal) {
        User user = userService.getByName(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
