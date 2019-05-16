package com.example.discovery.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
public class QuestionRestController {

  private static final Logger LOG = LoggerFactory.getLogger(QuestionRestController.class);

  private final DiscoveryClient discoveryClient;
  private final WebClient webClient;

  public QuestionRestController(DiscoveryClient discoveryClient, WebClient webClient) {
    this.discoveryClient = discoveryClient;
    this.webClient = webClient;
  }

  @GetMapping("/")
  Mono<String> questionAndAnswer() throws MalformedURLException {
    String url = null;
    List<String> services = this.discoveryClient.getServices();

    for (String service : services) {
      if (!service.equals("answering-service")) {
        continue;
      }
      List<ServiceInstance> instances = this.discoveryClient.getInstances(service);
      for (ServiceInstance se : instances) {
        Map<String, String> metadata = se.getMetadata();
        LOG.info("Metadata: {}", metadata);
        LOG.info("ServiceId: {}", se.getServiceId());
        LOG.info("URI: {}", se.getUri().toString());
        url = se.getUri().toString();
      }
    }

    if (url != null) {
      return webClient.get().uri(URI.create(url))
              .retrieve().bodyToMono(String.class)
              .map(a -> "What is the answer to the ultimate question of life, " +
                      "the universe and everything?\n"
                      + "The answer is '" + a + "'");
    } else {
      return Mono.just("What is the answer to the ultimate question of life, " +
              "the universe and everything?\n" + "The answer is unknown!!");
    }
  }
}
