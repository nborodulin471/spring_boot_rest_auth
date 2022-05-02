package com.borodulin.spring_boot_rest_auth.repository;

import com.borodulin.spring_boot_rest_auth.Authorities;
import com.borodulin.spring_boot_rest_auth.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static com.borodulin.spring_boot_rest_auth.Authorities.*;

@Repository
public class UserRepository {
    // учебная реализация хранилища
    private final Map<String, User> userAuthorities = Map.of(
            "admin", new User("admin", "123", List.of(READ, WRITE, DELETE)),
            "user", new User("user", "qwerty", List.of(READ))
    );

    public List<Authorities> getUserAuthorities(String login, String password) {
        User user = userAuthorities.get(login);
        if (user != null && user.getPassword().equals(password)) {
            return user.getPermissions();
        }
        return List.of();
    }
}
