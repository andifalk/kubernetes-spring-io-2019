package com.example.discovery.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AnsweringRestController {

  @GetMapping("/")
  Mono<String> questionAndAnswer() {
    return Mono.just("42");
  }
}
