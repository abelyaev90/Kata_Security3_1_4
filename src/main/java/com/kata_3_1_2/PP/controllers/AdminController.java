package com.kata_3_1_2.PP.controllers;


import com.kata_3_1_2.PP.entitys.User;
import com.kata_3_1_2.PP.service.RoleService;
import com.kata_3_1_2.PP.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

<<<<<<< HEAD
    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
=======
    public AdminController(UserService userService, RoleService roleService, RoleService roleService1, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService1;
>>>>>>> f053c696db8b3f6009e3eb0af5db18781e42d03f
        this.passwordEncoder = passwordEncoder;
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

    @GetMapping("/user-create")
    public String createUserForm(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        modelMap.addAttribute("roleList", roleService.getAll());
        return "user-create";
    }

    @PostMapping("users")
    public String createUser(@RequestParam("userName") String name, @RequestParam("userLastName") String lastname,
                             @RequestParam("userAge") int age, @RequestParam("userEmail") String email,
                             @RequestParam("userPassword") String password, @RequestParam("roles") String[] roles) {
    User user = new User();
    user.setUserName(name);
    user.setUserLastName(lastname);
    user.setUserAge(age);
    user.setUserEmail(email);
    user.setUserPassword(passwordEncoder.encode(password));
    user.setRoles(roleService.getByName(roles));
    userService.addUser(user);

        return "redirect:/admin/users";
    }


    @DeleteMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roleList", roleService.getAll());
        return "/user-update";
    }

    @PatchMapping("/user-update/{id}")
    public String updateUser(@RequestParam("userName") String name, @RequestParam("userLastName") String lastname,
                             @RequestParam("userAge") int age, @RequestParam("userEmail") String email,
                             @RequestParam("userPassword") String password, @RequestParam("roles") String[] roles, @PathVariable("id") Long id) {
        User userToBeUpdated = userService.getUserById(id);
        userToBeUpdated.setUserName(name);
        userToBeUpdated.setUserLastName(lastname);
        userToBeUpdated.setUserAge(age);
        userToBeUpdated.setUserEmail(email);
        userToBeUpdated.setUserPassword(passwordEncoder.encode(password));
        userToBeUpdated.setRoles(roleService.getByName(roles));
<<<<<<< HEAD
        userService.updateUser(userToBeUpdated);
=======
>>>>>>> f053c696db8b3f6009e3eb0af5db18781e42d03f
        return "redirect:/admin/users";
    }
}
