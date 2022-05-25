package com.borodulin.spring_boot_rest_auth.controller;

import com.borodulin.spring_boot_rest_auth.Authorities;
import com.borodulin.spring_boot_rest_auth.config.ResolveUser;
import com.borodulin.spring_boot_rest_auth.execption.InvalidCredentials;
import com.borodulin.spring_boot_rest_auth.execption.UnauthorizedUser;
import com.borodulin.spring_boot_rest_auth.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
public class AuthorizationController {

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@ResolveUser @Valid User user) {
        return user.getPermissions();
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationExceptions(MethodArgumentNotValidException e) {
        return e.getLocalizedMessage();
    }
    //</editor-fold>
}
