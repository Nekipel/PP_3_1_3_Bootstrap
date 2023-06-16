package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Person;
import ru.kata.spring.boot_security.demo.repositories.PersonRepositiry;
import ru.kata.spring.boot_security.demo.security.PersonDetails;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService{
    private final PersonRepositiry personRepositiry;

    @Autowired
    public PersonServiceImpl(PersonRepositiry personRepositiry) {
        this.personRepositiry = personRepositiry;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Person> userDetails = personRepositiry.findByUserName(username);
        if(userDetails.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        return new PersonDetails(userDetails.get());
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
    public void save(Person user) {
        personRepositiry.save(user);
    }

    @Override
    public void delete(Long id) {
        personRepositiry.delete(getUser(id));
    }
}
