package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Person;

import java.util.Optional;

@Repository
public interface PersonRepositiry extends JpaRepository<Person,Long> {

    @Query("Select u from Person u left join fetch u.roles where u.userName=:username")
    Optional<Person> findByUserName (String username);
//    Optional<Person> findByUserName (String username);
}
