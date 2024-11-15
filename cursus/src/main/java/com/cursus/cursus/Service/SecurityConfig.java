package com.cursus.cursus.Service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/admin/**").hasRole("ADMIN")  // Seuls les utilisateurs avec le rôle ROLE_ADMIN peuvent accéder à /admin/**
                .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")  // Les utilisateurs avec le rôle ROLE_USER ou ROLE_ADMIN peuvent accéder à /user/**
                .requestMatchers("/public/**").permitAll()  // Accès public
                .anyRequest().authenticated()  // Toute autre demande nécessite une authentification
                .and()
                .formLogin()
                .permitAll()  // Permet à tout le monde d'accéder à la page de connexion
                .and()
                .logout()
                .permitAll();  // Permet à tout le monde d'accéder à la page de déconnexion
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
