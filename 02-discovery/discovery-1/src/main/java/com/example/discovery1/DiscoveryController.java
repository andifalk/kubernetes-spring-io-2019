package com.example.discovery1;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DiscoveryController {

  private final DiscoveryClient discoveryClient;

  public DiscoveryController(DiscoveryClient discoveryClient) {
    this.discoveryClient = discoveryClient;
  }

  @GetMapping("/")
  List<String> discovery() {
    return discoveryClient.getServices();
  }
}
