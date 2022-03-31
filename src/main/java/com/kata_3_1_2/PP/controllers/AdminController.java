package com.kata_3_1_2.PP.controllers;



import com.kata_3_1_2.PP.entitys.User;
import com.kata_3_1_2.PP.service.RoleService;
import com.kata_3_1_2.PP.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping()
    public String printUsers(Principal principal, Model model) {
        model.addAttribute("users",userService.listUsers());
        model.addAttribute("user", new User());
        model.addAttribute("currentUser", userService.getByName(principal.getName()));
        /*List<User> users = userService.listUsers();
        model.addAttribute("users", users);*/
        return "users-list";
    }

    @GetMapping("/user-create")
    public String createUserForm(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        modelMap.addAttribute("roleList", roleService.getAll());
        return "user-create";
    }
//метод ниже убрать, он заменен на  public String addUser(@ModelAttribute("user")User user,
//                          @RequestParam("roles") String[] roles)
/*    @PostMapping("users")
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

        return "redirect:/admin";
    }*/

    ///---------

    @PostMapping()
    public String addUser(@ModelAttribute("user")User user,
                          @RequestParam("roles") String[] roles){
        user.setUserPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roleService.getByName(roles));
        userService.addUser(user);
        return "redirect:/admin";
    }

    ////------


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

    //метод ниже удалить, заменен на public String editUser(@ModelAttribute("user") User user,
    //                           @PathVariable ("id")long id, @RequestParam("roles") String[] roles)
/*
    @PatchMapping("/{id}")
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
        userService.updateUser(userToBeUpdated);

        return "redirect:/admin";
    }
*/

    //----------

    @PutMapping("/{id}")
    public String editUser(@ModelAttribute("user") User user,
                           @PathVariable ("id") long id, @RequestParam("roles") String[] roles){

  /*      for (String nameRole:roles) {
            user.setRoles(roleService.getByNameStr(nameRole));
        }*/

        if(roles!= null){

           user.setRoles(roleService.getByName(roles));
        } else{
            user.setRoles(userService.getUserById(user.getId()).getRoles());
        }
        if(user.getPassword().equals("")){
            user.setUserPassword(userService.getUserById(user.getId()).getPassword());
        } else {
            user.setUserPassword(passwordEncoder.encode(user.getPassword()));
        }
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    //-----
}
