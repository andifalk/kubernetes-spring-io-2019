package com.example.hello;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "hello")
public class MessageConfig {
    private String message = "World";
    private String prefix = "Hello";

    public String getMessage() {
        return message;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}