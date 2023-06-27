package ru.kata.spring.boot_security.demo.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;
import ru.kata.spring.boot_security.demo.services.RoleServiceImpl;

import java.util.HashSet;
import java.util.Set;

@Component
public class SetupDataLoader implements CommandLineRunner {
    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;
    private final PasswordEncoder passwordEncoder;

    public SetupDataLoader(UserServiceImpl userService, RoleServiceImpl roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        Set<Role> adminRoles = new HashSet<>();
        Set<Role> userRoles = new HashSet<>();
        roleService.addRole(roleAdmin);
        roleService.addRole(roleUser);
        adminRoles.add(roleAdmin);
        adminRoles.add(roleUser);
        userRoles.add(roleUser);

        User userAdmin = new User("admin","admin", passwordEncoder.encode("admin"), 32);
        User userUser = new User("user","user", passwordEncoder.encode("user"), 32);
        System.out.println(userAdmin);
        userAdmin.setRoles(adminRoles);
        userService.save(userAdmin);
        userUser.setRoles(userRoles);
        System.out.println(userUser);
        userService.save(userUser);
    }
}
