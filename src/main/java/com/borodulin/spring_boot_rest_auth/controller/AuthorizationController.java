package com.borodulin.spring_boot_rest_auth.controller;

import com.borodulin.spring_boot_rest_auth.Authorities;
import com.borodulin.spring_boot_rest_auth.execption.InvalidCredentials;
import com.borodulin.spring_boot_rest_auth.execption.UnauthorizedUser;
import com.borodulin.spring_boot_rest_auth.service.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorizationController {
    private final AuthorizationService service;

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@RequestParam("user") String user, @RequestParam("password") String password) {
        return service.getAuthorities(user, password);
    }

    //<editor-fold desc="handleException">
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidCredentials.class)
    private String handleInvalidCredentials(InvalidCredentials e) {
        return e.getLocalizedMessage();
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedUser.class)
    private String handleUnauthorizedUser(UnauthorizedUser e) {
        System.out.println(e.getLocalizedMessage());
        return e.getLocalizedMessage();
    }
    //</editor-fold>
}
