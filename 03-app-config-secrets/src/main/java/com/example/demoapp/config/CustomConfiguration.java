package com.example.demoapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "custom")
public class CustomConfiguration {

    private String message = "Hello Spring Boot";

    private String password = "secret";

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public String getPassword() {
        return password;
    }

}