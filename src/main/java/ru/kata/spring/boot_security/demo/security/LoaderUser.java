package ru.kata.spring.boot_security.demo.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.Optional;

@Service
public class LoaderUser implements UserDetailsService {
    private final UserRepository userRepository;

    public LoaderUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userDetails = userRepository.findByUserName(username);
        if(userDetails.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        return new UserDetails(userDetails.get());
    }
}
