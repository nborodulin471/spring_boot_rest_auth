package com.borodulin.spring_boot_rest_auth.repository;

import com.borodulin.spring_boot_rest_auth.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.borodulin.spring_boot_rest_auth.Authorities.*;

@Repository
public class UserRepository {
    // учебная реализация хранилища
    private final Map<String, User> userAuthorities = Map.of(
            "admin", new User("admin", "12345", List.of(READ, WRITE, DELETE)),
            "user", new User("user", "qwerty123", List.of(READ)),
            "Petya", new User("Petya", "1", null)
    );

    public Optional<User> getUser(String login, String password) {
        User user = userAuthorities.get(login);
        if (user != null && user.getPassword().equals(password)) {
            return Optional.of(user);
        }
        return Optional.empty();
    }
}
