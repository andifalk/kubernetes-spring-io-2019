package com.example.discovery.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class QuestionClientApplication {

  public static void main(String[] args) {
    SpringApplication.run(QuestionClientApplication.class, args);
  }
}
