package com.projects.libraryproject.controller.unauthenticated;

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
@RequestMapping("/")
public class UnauthenticatedUserController {

    private final UserServiceImplementation userServiceImplementation;

    @Autowired
    public UnauthenticatedUserController(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }

    @GetMapping("/new")
    public String createUserForm(Model model) {
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "/user/create_user";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute("user") UserEntity user) {
        userServiceImplementation.saveUser(user);
        return "redirect:/user/users";
    }


}
