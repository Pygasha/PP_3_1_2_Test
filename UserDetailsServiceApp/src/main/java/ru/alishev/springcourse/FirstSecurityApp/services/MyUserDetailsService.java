package ru.alishev.springcourse.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.alishev.springcourse.FirstSecurityApp.models.User;
import ru.alishev.springcourse.FirstSecurityApp.repositories.UserRepository;
import ru.alishev.springcourse.FirstSecurityApp.security.MyUserDetails;

import java.util.Optional;

/**
 * @author Neil Alishev
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository peopleRepository;

    @Autowired
    public MyUserDetailsService(UserRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> person = peopleRepository.findByUsername(s);

        if (person.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return new MyUserDetails(person.get());
    }
}
