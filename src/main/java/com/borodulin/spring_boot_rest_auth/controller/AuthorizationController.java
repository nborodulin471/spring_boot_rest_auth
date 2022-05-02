package com.borodulin.spring_boot_rest_auth.controller;

import com.borodulin.spring_boot_rest_auth.Authorities;
import com.borodulin.spring_boot_rest_auth.config.ResolveUser;
import com.borodulin.spring_boot_rest_auth.execption.InvalidCredentials;
import com.borodulin.spring_boot_rest_auth.execption.UnauthorizedUser;
import com.borodulin.spring_boot_rest_auth.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT) // XD
    @ResponseBody
    private String handleUnauthorizedUser(Exception e) {
        return e.getLocalizedMessage();
    }
    //</editor-fold>
}
