package com.kata_3_1_2.PP.controllers;


import com.kata_3_1_2.PP.entitys.User;
import com.kata_3_1_2.PP.service.RoleService;
import com.kata_3_1_2.PP.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
    }

    @GetMapping()
    public String adminPrint() {
        return "admin";
    }

    @GetMapping("/users")
    public String printUsers(Model model) {
        List<User> users = userService.listUsers();
        model.addAttribute("users", users);
        return "users-list";
    }


//*****************************************************************
    @GetMapping("/user-create")
    public String createUserForm(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        return "user-create";
    }

    //изменить
    @PostMapping("users")
    public String createUser(@ModelAttribute("user") User user, String[] roles) {
        userService.addUser(user, roles);
        return "redirect:/users";
    }


    //*****************************************************************



    @DeleteMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "/user-update";
    }

    //изменить как create
    @PostMapping("/user-update")
    public String updateUser(User user, String[] roles) {
        userService.updateUser(user, roles);
        return "redirect:/users";
    }
}
