package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.Person;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.services.PersonServiceImpl;

import java.security.Principal;
import java.util.Set;

@Controller
@RequestMapping("/")
public class AuthController {

    private final PersonServiceImpl personService;

    public AuthController(PersonServiceImpl personService) {
        this.personService = personService;
    }

    @GetMapping("/user")
    public String getUserPage(Model model, Principal principal) {
        String username = principal.getName();
        Person person = personService.findByName(username);
        Set<Role> roles = person.getRoles();
        model.addAttribute("person", person);
        model.addAttribute("roles", roles);
        return "/user";
    }
}
