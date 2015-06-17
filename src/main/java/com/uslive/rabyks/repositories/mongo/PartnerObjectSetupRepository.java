package com.uslive.rabyks.repositories.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.uslive.rabyks.models.mongo.PartnerObjectSetup;

public interface PartnerObjectSetupRepository extends MongoRepository<PartnerObjectSetup, String>{

	PartnerObjectSetup findByPartnerId(int id);
}
