package com.projects.libraryproject.controller.user;

import com.projects.libraryproject.entity.AddressEntity;
import com.projects.libraryproject.entity.PostEntity;
import com.projects.libraryproject.entity.RoleEntity;
import com.projects.libraryproject.entity.UserEntity;
import com.projects.libraryproject.service.Implementation.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/user/users")
public class UserUserController {

    private final UserServiceImplementation userServiceImplementation;

    @Autowired
    public UserUserController(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }


    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable long id, Model model) {
        UserEntity user = userServiceImplementation.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("address", user.getAddress());
        model.addAttribute("post", user.getAddress().getPost());
        model.addAttribute("role", user.getRole());
        return "/user/edit_user";
    }


    @PostMapping("/{id}")
    public String updateUser(@PathVariable long id, @ModelAttribute("user") UserEntity user,
                             @ModelAttribute("address") AddressEntity address, @ModelAttribute("post") PostEntity post,
                             @ModelAttribute("role") RoleEntity role) {
        user.setUserId(id);
        userServiceImplementation.updateUser(user, address, post, role);
        return "redirect:/user/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        userServiceImplementation.deleteUserById(id);
        return "redirect:/user/users";
    }

}
