package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import java.security.Principal;
import java.util.Set;

@Controller
@RequestMapping("/")
public class AuthController {

    private final UserServiceImpl personService;

    public AuthController(UserServiceImpl personService) {
        this.personService = personService;
    }

    @GetMapping("/user")
    public String getUserPage(Model model, Principal principal) {
        String username = principal.getName();
        User user = personService.findByName(username);
        Set<Role> roles = user.getRoles();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "/user";
    }
}
