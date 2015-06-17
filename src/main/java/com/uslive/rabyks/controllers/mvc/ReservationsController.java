package com.uslive.rabyks.controllers.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.uslive.rabyks.repositories.mongo.ReservationsRepository;

@Controller
public class ReservationsController {

	private Logger log = LoggerFactory.getLogger(ReservationsController.class);

	@Autowired
	private ReservationsRepository reservationsRepo;

	
	
}
