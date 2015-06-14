package com.uslive.rabyks.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.uslive.rabyks.controllers.websocket.DragstorSocketServer;
import com.uslive.rabyks.controllers.websocket.TerasaSocketServer;

@SpringBootApplication
@ComponentScan(basePackages="com.uslive.rabyks")
@PropertySource("classpath:application.properties")
@EntityScan(basePackages="com.uslive.rabyks.models.mysql")
@EnableMongoRepositories(basePackages="com.uslive.rabyks.repositories.mongo")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="com.uslive.rabyks.repositories.mysql")
public class Application implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		new DragstorSocketServer().start();
		new TerasaSocketServer().start();
	}
}
