package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Person;
import ru.kata.spring.boot_security.demo.repositories.PersonRepositiry;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService{
    private final PersonRepositiry personRepositiry;

    public PersonServiceImpl(PersonRepositiry personRepositiry) {
        this.personRepositiry = personRepositiry;
    }

    @Override
    public List<Person> findAll() {
        return personRepositiry.findAll();
    }

    @Override
    public Person findByName(String username) {
        return personRepositiry.findByUserName(username).get();
    }

    @Override
    public Person getUser(Long id) {
        return personRepositiry.getById(id);
    }

    @Override
    @Transactional
    public void save(Person user) {
        personRepositiry.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        personRepositiry.delete(getUser(id));
    }
}
