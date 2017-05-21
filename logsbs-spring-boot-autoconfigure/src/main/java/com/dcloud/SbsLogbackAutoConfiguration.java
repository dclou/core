package com.dcloud;

import net.logstash.logback.appender.LogstashTcpSocketAppender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by apodoplelov on 12.05.17.
 */
@Configuration
@ConditionalOnClass(LogstashTcpSocketAppender.class)
public class SbsLogbackAutoConfiguration {

    private @Value("${dcloud.logstash.url}") String destinationAddress;

    private @Value("${dcloud.logstash.loggers}") String logstashLoggers;

    @Bean(name = "logstashAppender")
    public DripLogstashAppender logstashAppender() {
        DripLogstashAppender appender = new DripLogstashAppender(logstashLoggers);
        appender.addDestination(destinationAddress);
        return appender;
    }

}
