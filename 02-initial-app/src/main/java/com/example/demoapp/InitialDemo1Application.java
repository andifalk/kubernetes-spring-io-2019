package com.example.demoapp;

import com.example.demoapp.config.CustomConfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(CustomConfiguration.class)
public class InitialDemo1Application {

	public static void main(String[] args) {
		SpringApplication.run(InitialDemo1Application.class, args);
	}

}
