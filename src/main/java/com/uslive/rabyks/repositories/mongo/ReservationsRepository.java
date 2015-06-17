package com.uslive.rabyks.repositories.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.uslive.rabyks.models.mongo.Reservation;

public interface ReservationsRepository extends MongoRepository<Reservation, String> {

}
