package com.projects.libraryproject.controller;

import com.projects.libraryproject.entity.AddressEntity;
import com.projects.libraryproject.entity.LibraryEntity;
import com.projects.libraryproject.entity.PostEntity;
import com.projects.libraryproject.entity.UserEntity;
import com.projects.libraryproject.service.Implementation.UserServiceImplementation;
import com.projects.libraryproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
