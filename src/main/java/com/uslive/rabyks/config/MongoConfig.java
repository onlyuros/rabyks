package com.uslive.rabyks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

@Configuration
@EnableMongoRepositories(basePackages="com.uslive.rabyks.repositories.mongo")
public class MongoConfig extends AbstractMongoConfiguration {

	@Autowired
	Environment env;
	
    @Override
    protected String getDatabaseName() {
        return env.getProperty("mongo.db");
    }

    @Override
    protected String getMappingBasePackage() {
        return "com.uslive.rabyks.models.mongo";
    }
    
    @Override
    @Bean
    public MongoClient mongo() throws Exception {
        MongoClient client = new MongoClient(env.getProperty("mongo.host"));
        client.setWriteConcern(WriteConcern.SAFE);
        return client;
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), getDatabaseName());
    }
}

