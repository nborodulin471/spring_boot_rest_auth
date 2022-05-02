package com.borodulin.spring_boot_rest_auth.model;

import com.borodulin.spring_boot_rest_auth.Authorities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private String login;
    private String password;
    private List<Authorities> permissions;
}
