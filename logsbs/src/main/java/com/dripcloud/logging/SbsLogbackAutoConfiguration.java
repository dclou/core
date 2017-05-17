package com.dripcloud.logging;

import net.logstash.logback.appender.LogstashTcpSocketAppender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by apodoplelov on 12.05.17.
 */
@Configuration
@ConditionalOnClass(LogstashTcpSocketAppender.class)
public class SbsLogbackAutoConfiguration {

    private @Value("${dcloud.logstash.url}") String destinationAddress;

    @Bean(name = "logstashAppender")
    public DripLogstashAppender logstashAppender() {
        DripLogstashAppender appender = new DripLogstashAppender();
        appender.addDestination(destinationAddress);
        return appender;
    }

}
