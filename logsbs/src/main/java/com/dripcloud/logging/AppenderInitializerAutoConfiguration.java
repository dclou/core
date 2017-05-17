package com.dripcloud.logging;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by apodoplelov on 17.05.17.
 */
public class AppenderInitializerAutoConfiguration implements ApplicationContextInitializer {

    public void initialize(ConfigurableApplicationContext ctx) {
        System.out.println("AppenderInitializerAutoConfiguration's initialize method called.");
    }
}