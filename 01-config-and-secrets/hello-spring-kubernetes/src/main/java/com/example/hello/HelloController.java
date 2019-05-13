package com.example.hello;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@RefreshScope
@RestController
@RequestMapping("/")
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);
    
    private final String defaultMessage;

    private final String defaultPrefix;

    public HelloController(@Autowired MessageConfig messageConfig) {
        this.defaultPrefix = messageConfig.getPrefix();
        this.defaultMessage = messageConfig.getMessage();  
        LOGGER.info("prefix '{}'", defaultPrefix);      
        LOGGER.info("message '{}'", defaultMessage);      
    }

    @ResponseStatus(OK)
    @GetMapping
    public Message sayHello() {
        return new Message(String.format("%s %s", defaultPrefix, defaultMessage));
    }

    @ResponseStatus(CREATED)
    @PostMapping
    public Message sayHelloInBody(@RequestBody String message) {
        return new Message(defaultPrefix + " " + (message != null ? message : defaultMessage));
    }

}