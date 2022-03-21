package com.kata_3_1_2.PP.controllers;


import com.kata_3_1_2.PP.entitys.User;
import com.kata_3_1_2.PP.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/show")
    public String showUser(Principal principal, ModelMap modelMap) {
        User userBD = userService.getByName(principal.getName());
        modelMap.addAttribute("user", userBD);
        return "show";
    }

    @GetMapping()
    public String userPrint() {
        return "user";
    }
}
