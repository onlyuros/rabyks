package com.uslive.rabyks.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@PropertySource("classpath:application.properties")
@EntityScan(basePackages="com.uslive.rabyks.models.mysql")
@EnableMongoRepositories(basePackages="com.uslive.rabyks.repositories.mongo")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="com.uslive.rabyks.repositories.mysql")
@ComponentScan(basePackages="com.uslive.rabyks")
public class Application{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
