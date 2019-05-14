package com.example.hello;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class HelloUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloUserService.class);

    @Value("${user.username:user}")
    private String userUsername;

    @Value("${user.password:secret}")
    private String userPassword;

    @Value("${admin.username:admin}")
    private String adminUsername;

    @Value("${admin.password:secret}")
    private String adminPassword;

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    UserDetailsService helloUserDetailsService() {
        LOGGER.info("Password for user '{}': '{}'", userUsername, userPassword);
        LOGGER.info("Password for user '{}': '{}'", adminUsername, adminPassword);
        return new InMemoryUserDetailsManager(
            Arrays.asList(
                User.withUsername(userUsername).password(passwordEncoder().encode(userPassword)).roles("USER").build(),
                User.withUsername(adminUsername).password(passwordEncoder().encode(adminPassword)).roles("USER", "ADMIN").build()
            )
        );
    }
}