package com.dachen.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@ComponentScan("com.dachen.demo")
@EnableAsync
@PropertySource("classpath:application.properties")
public class App
{
	public static void main(String[] args) {
		new SpringApplicationBuilder(App.class).web(true).run(args);
    }
}
