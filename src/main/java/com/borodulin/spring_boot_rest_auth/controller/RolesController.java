package com.borodulin.spring_boot_rest_auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/")
public class RolesController {
    @Secured("ROLE_READ")
    @GetMapping("/secured")
    public String secured() {
        return "secured";
    }

    @RolesAllowed("ROLE_WRITE")
    @GetMapping("/allowed")
    public String allowed() {
        return "allowed";
    }

    @PreAuthorize("hasRole('ROLE_WRITE') or hasRole('ROLE_DELETE')")
    @GetMapping("/pre")
    public ResponseEntity<String> pre(@RequestParam String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName().equals(username)) {
            return ResponseEntity.ok("Ok");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
