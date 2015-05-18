package com.uslive.rabyks.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.uslive.rabyks.models.PartnerObjectSetup;

@Repository
public class PartnerObjectSetupRepository {
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	public long countAllPartnerObjectSetupTableRows() {
	    return mongoTemplate.count(null, PartnerObjectSetup.class);
	}
	
	public PartnerObjectSetup findByUserName(String username) {
		Query query = new Query(Criteria.where("name").is("mkyong"));
		return mongoTemplate.findOne(query, PartnerObjectSetup.class);
	}
	
	public PartnerObjectSetup findById(String id) {
		return mongoTemplate.findById(id, PartnerObjectSetup.class);
	}
}
