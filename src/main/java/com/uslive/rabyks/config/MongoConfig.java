package com.uslive.rabyks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories
public class MongoConfig extends AbstractMongoConfiguration {

	@Autowired
	Environment env;
	
    @Override
    protected String getDatabaseName() {
        return env.getProperty("mongo.db");
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(env.getProperty("mongo.host"), Integer.parseInt(env.getProperty("mongo.port")));

    }

    @Override
    protected String getMappingBasePackage() {
        return "com.uslive.rabyks.model	";
    }
}

