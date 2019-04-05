package com.example.demoapp.api;

import com.example.demoapp.config.CustomConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Demo1RestController {

    private final CustomConfiguration customConfiguration;

    public Demo1RestController(@Autowired CustomConfiguration customConfiguration) {
        this.customConfiguration = customConfiguration;
    }

    @GetMapping
    public String sayHello() {
        return customConfiguration.getMessage();
    }
}