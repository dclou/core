package com.dcloudsample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldService {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorldService.class);

    public String getHelloMessage() {
        logger.info("HelloWorldService");
        return "Hello!";
    }

}