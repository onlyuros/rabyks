package com.uslive.rabyks.repositories.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.uslive.rabyks.models.mongo.PartnerObjectSetup;

public interface PartnerObjectSetupRepository extends MongoRepository<PartnerObjectSetup, String>{

	List<PartnerObjectSetup> findByPartnerId(int id);
}
