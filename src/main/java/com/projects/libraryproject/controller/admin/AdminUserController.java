package com.projects.libraryproject.controller.admin;

import com.projects.libraryproject.entity.*;
import com.projects.libraryproject.service.Implementation.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin/users")
public class AdminUserController {

    private final UserServiceImplementation userServiceImplementation;

    @Autowired
    public AdminUserController(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userServiceImplementation.getAllUsers());
        return "/admin/users";
    }

    @GetMapping("/new")
    public String createUserForm(Model model) {
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "/admin/create_user";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute("user") UserEntity user) {
        userServiceImplementation.saveUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable long id, Model model) {
        UserEntity user = userServiceImplementation.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("address", user.getAddress());
        model.addAttribute("post", user.getAddress().getPost());
        model.addAttribute("role", user.getRole());
        return "/admin/edit_user";
    }


    @PostMapping("/{id}")
    public String updateUser(@PathVariable long id, @ModelAttribute("user") UserEntity user,
                             @ModelAttribute("address") AddressEntity address, @ModelAttribute("post") PostEntity post,
                             @ModelAttribute("role") RoleEntity role) {
        user.setUserId(id);
        userServiceImplementation.updateUser(user, address, post, role);
        return "redirect:/admin/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        userServiceImplementation.deleteUserById(id);
        return "redirect:/admin/users";
    }

}
