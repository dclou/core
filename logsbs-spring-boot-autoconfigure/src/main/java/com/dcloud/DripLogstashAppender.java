package com.dcloud;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import net.logstash.logback.appender.LogstashTcpSocketAppender;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by apodoplelov on 18.05.17.
 */
public class DripLogstashAppender extends LogstashTcpSocketAppender {

    private final String logstashLoggers;

    DripLogstashAppender(String logstashLoggers) {
        this.logstashLoggers = logstashLoggers;
    }

    @PostConstruct
    private void configureLogback() throws IOException {

        // assume SLF4J is bound to logback in the current environment
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

        Optional.ofNullable(logstashLoggers)
                .ifPresent(
                        s -> Stream.of(s.split(","))
                                .forEach(lName -> {
                                    Optional.ofNullable(context.getLogger(lName))
                                            .ifPresent(l -> l.addAppender(this));
                                }));
    }

}
