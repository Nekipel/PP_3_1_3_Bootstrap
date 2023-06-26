package ru.kata.spring.boot_security.demo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Person;
import ru.kata.spring.boot_security.demo.repositories.PersonRepositiry;

import java.util.Optional;

@Service
public class LoaderUser implements UserDetailsService {
    private final PersonRepositiry personRepositiry;

    public LoaderUser(PersonRepositiry personRepositiry) {
        this.personRepositiry = personRepositiry;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Person> userDetails = personRepositiry.findByUserName(username);
        if(userDetails.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        return new PersonDetails(userDetails.get());
    }
}
