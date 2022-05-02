package com.borodulin.spring_boot_rest_auth.service;

import com.borodulin.spring_boot_rest_auth.execption.InvalidCredentials;
import com.borodulin.spring_boot_rest_auth.model.User;
import com.borodulin.spring_boot_rest_auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService {
    private final UserRepository userRepository;

    public User getUser(String login, String password) {
        return userRepository.getUser(login, password)
                .orElseThrow(() -> new InvalidCredentials("User name or password is empty"));
    }
}