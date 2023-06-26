package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.models.Person;

import java.util.List;

public interface PersonService {
    List<Person> findAll();

    Person findByName(String username);

    Person getUser(Long id);

    void save(Person user);

    void delete(Long id);
}
