package com.dripcloud.logging;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import net.logstash.logback.appender.LogstashTcpSocketAppender;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by apodoplelov on 18.05.17.
 */
public class DripLogstashAppender extends LogstashTcpSocketAppender implements InitializingBean {

    private @Value("${dcloud.logstash.loggers}") String logstashLoggers;

    @Autowired
    private ApplicationContext ctx;

    public void afterPropertiesSet() throws Exception {
        configureLogback();

/*
        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println("B: " + beanName);
        }
*/

    }

    private void configureLogback() throws IOException {

        // assume SLF4J is bound to logback in the current environment
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
/*
        Logger r = context.getLogger("ROOT");
        System.out.println("Root: " + r.getName() + ", Level: " + r.getLevel());
        for (Iterator<Appender<ILoggingEvent>> it = r.iteratorForAppenders(); it.hasNext(); ) {
            Appender a = it.next();
            System.out.println("Root appender: " + a.getName());
        }
*/

        if (logstashLoggers != null) {
            String[] lNames = logstashLoggers.split(",");
            if (lNames.length > 0) {
                for (String lName : lNames) {
                    Logger l = context.getLogger(lName);
                    if (l != null) {
                        l.addAppender(this);
                        System.out.println("Added logstash appender to: " + l.getName());
                    }
                }
            }
        }
/*
        List<Logger> loggers = context.getLoggerList();

        for (Logger logger : loggers) {
            System.out.println("L: " + logger.getName());
        }
*/
/*
        try {
            JoranConfigurator jc = new JoranConfigurator();
            jc.setContext(context);
            context.reset(); // override default configuration
            // inject the name of the current application as "application-name"
            // property of the LoggerContext
            context.putProperty("LOG_DIR", logDir);
            context.putProperty("LOG_NAME", logName);

            context.putProperty("ERROR_MAIL_PASSWORD", logbackErrorMailPassword);
            context.putProperty("SUPPORT_EMAIL_ID", supportEmail);
            context.putProperty("ENV", env);
            //jc .doConfigure(servletContext.getRealPath("/WEB-INF/my-logback.xml"));
            jc.doConfigure(new ClassPathResource("my-logback.xml").getInputStream());
        } catch (JoranException je) {
            // StatusPrinter will handle this
        }
        StatusPrinter.printInCaseOfErrorsOrWarnings(context);
*/

    }

}
