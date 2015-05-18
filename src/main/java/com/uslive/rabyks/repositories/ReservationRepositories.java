package com.uslive.rabyks.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationRepositories {

	@Autowired
	MongoTemplate mongoTemplate;
}
