package com.borodulin.spring_boot_rest_auth.model;

import com.borodulin.spring_boot_rest_auth.Authorities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Validated
@AllArgsConstructor
public class User {
    @NotBlank(message = "Логин не может быть пустым")
    private String login;

    @Size(min = 5)
    @NotBlank(message = "Пароль должен быть заполнен и содержать минимум 10 символов")
    private String password;

    @NotEmpty(message = "Права не должны быть пустыми")
    private List<Authorities> permissions;
}
