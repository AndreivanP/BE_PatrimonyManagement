package com.andreivan.rest.webservices.patrimonymanagement.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

    static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

    static {
        inMemoryUserList.add(new JwtUserDetails(1L, "Juca",
                "$2a$10$1XDdY3MfETkJfeYl8RW3EOPebc.hSado2OpmLhV7IpW1a3ry/NlfO", "ROLE_USER_2"));

        inMemoryUserList.add(new JwtUserDetails(1L, "Andreivan",
                "$2a$10$v7ZRXNqlpoZ6n5BXNnp9HOnr3bwJV8vGM0U4tZz8knuHZpPRoSXxe", "ROLE_USER_2"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
                .filter(user -> user.getUsername().equals(username)).findFirst();

        if (!findFirst.isPresent()) {
            throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
        }

        return findFirst.get();
    }

}
