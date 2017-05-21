package com.dcloud;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.EnvironmentTestUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

public class LogsbsSpringBootAutoconfigureApplicationTests {

	private AnnotationConfigApplicationContext context;

	@Before
	public void init() {
		this.context = new AnnotationConfigApplicationContext();
	}

	@After
	public void closeContext() {
		if (this.context != null) {
			this.context.close();
		}
	}

	@Test
	public void testWithTwitter4jProperties(){

		EnvironmentTestUtils.addEnvironment(this.context,
				"dcloud.logstash.url:127.0.0.1:5556",
				"dcloud.logstash.loggers:com.dripcloud.HelloController,ROOT");
		this.context.register(
				PropertyPlaceholderAutoConfiguration.class,
				SbsLogbackAutoConfiguration.class
		);
		this.context.refresh();
		assertEquals(1, this.context.getBeanNamesForType(DripLogstashAppender.class).length);

	}

}
