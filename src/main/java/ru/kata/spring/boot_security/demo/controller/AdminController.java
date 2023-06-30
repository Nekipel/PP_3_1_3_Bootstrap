package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.RoleService;

import java.security.Principal;

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
    public String getAllUsers(Model model, Principal principal){
        model.addAttribute("users", userService.findAll());
        model.addAttribute("user", userService.findByName(principal.getName()));
        return "admin";
    }
    @GetMapping("/{id}")
    public String read(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "redirect:/admin";
    }
    @GetMapping("/new")
    public String registrationPage(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("rolesAdd", roleService.getAllRoles());
        return "redirect:/admin";
    }
    @PostMapping()
    public String performRegistration(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUser(id));
        return "admin";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.upDate(user);
        return "redirect:/admin";
    }

    // Удаление пользователя
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.delete(userService.getUser(id).getId());
        return "redirect:/admin";
    }
}
