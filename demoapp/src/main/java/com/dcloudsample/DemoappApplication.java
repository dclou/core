package com.dcloudsample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoappApplication implements CommandLineRunner{

	@Autowired
	private ApplicationContext context;

	@Autowired
	private HelloWorldService helloWorldService;

	@Override
	public void run(String... args) {
		System.out.println(this.helloWorldService.getHelloMessage());
		Object appender = context.getBean("logstashAppender");
		System.out.println(appender.toString());
	}

	public static void main(String[] args) throws Exception {
		args = new String[]{"--debug"};
		SpringApplication.run(DemoappApplication.class, args);
	}
}
