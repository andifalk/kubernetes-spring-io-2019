package com.example.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(MessageConfig.class)
public class HelloSpringCloudKubernetesApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringCloudKubernetesApplication.class, args);
	}

}
