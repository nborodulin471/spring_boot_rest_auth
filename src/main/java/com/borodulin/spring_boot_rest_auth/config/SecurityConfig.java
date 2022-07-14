package com.borodulin.spring_boot_rest_auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}1").roles("READ", "WRITE", "DELETE").and()
                .withUser("user").password("{noop}1").roles("READ").and()
                .withUser("peter").password("{noop}1").roles("DELETE", "WRITE");
    }

    @Override
    protected void configure(HttpSecurity req) throws Exception {
        req.formLogin().and()
                .authorizeHttpRequests()
                .antMatchers("/authorize")
                .authenticated()
                .and()
                .authorizeHttpRequests()
                .anyRequest()
                .permitAll();
    }
}
