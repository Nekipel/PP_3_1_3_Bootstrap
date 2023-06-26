package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Person;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.PersonRepositiry;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import java.util.HashSet;
import java.util.Set;
@Component
public class SetupDataLoader implements CommandLineRunner {
    private final PersonRepositiry personRepositiry;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public SetupDataLoader(PersonRepositiry personRepositiry, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.personRepositiry = personRepositiry;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        Set<Role> adminRoles = new HashSet<>();
        Set<Role> userRoles = new HashSet<>();
        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);
        adminRoles.add(roleAdmin);
        adminRoles.add(roleUser);
        userRoles.add(roleUser);


        // пользователи Admin и User
        Person userAdmin = new Person("admin","admin", passwordEncoder.encode("admin"), 32);
        Person userUser = new Person("user","user", passwordEncoder.encode("user"), 32);
        System.out.println(userAdmin);
        userAdmin.setRoles(adminRoles);
        personRepositiry.save(userAdmin);
        userUser.setRoles(userRoles);
        System.out.println(userUser);
        personRepositiry.save(userUser);
    }
}
