package com.uslive.rabyks.repositories.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.uslive.rabyks.models.mongo.ConfirmedReservation;

public interface ConfirmedReservationsRepository extends MongoRepository<ConfirmedReservation, String> {

}
