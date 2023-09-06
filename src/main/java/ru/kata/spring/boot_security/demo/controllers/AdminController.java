package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String getAllUsers(@ModelAttribute("user") User user, Model model,
                              Principal principal) {
        model.addAttribute("roles", roleService.getRoles());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("authenticatedUser", userService.findByUsername(principal.getName()));
        return "all_users";
    }


    @PatchMapping("/update/{id}")
    public String updateUser(@ModelAttribute("user") User updateUser, @PathVariable("id") Long id) {
        userService.updateUser(updateUser, id); //Находим по id того юзера, которого надо изменить
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }


    @PostMapping("/create")
    public String saveNewUser(@ModelAttribute("user") User user) {
        userService.saveUser(user); // Добавляем этого юзера в БД
        return "redirect:/admin";
    }
}
