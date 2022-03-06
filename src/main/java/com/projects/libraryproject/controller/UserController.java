package com.projects.libraryproject.controller;

import com.projects.libraryproject.entity.*;
import com.projects.libraryproject.service.Implementation.UserServiceImplementation;
import com.projects.libraryproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserServiceImplementation userServiceImplementation;

    @Autowired
    public UserController(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userServiceImplementation.getAllUsers());
        return "users";
    }

    @GetMapping("/new")
    public String createUserForm(Model model) {
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "create_user";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute("user") UserEntity user) {
        userServiceImplementation.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable long id, Model model) {
        UserEntity user = userServiceImplementation.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("address", user.getAddress());
        model.addAttribute("post", user.getAddress().getPost());
        model.addAttribute("role", user.getRole());
        return "edit_user";
    }


    @PostMapping("/{id}")
    public String updateUser(@PathVariable long id, @ModelAttribute("user") UserEntity user,
                             @ModelAttribute("address") AddressEntity address, @ModelAttribute("post") PostEntity post,
                             @ModelAttribute("role") RoleEntity role, Model model
                             ) {
        user.setUserId(id);
        userServiceImplementation.updateUser(user, address, post, role);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        userServiceImplementation.deleteUserById(id);
        return "redirect:/users";
    }

}
