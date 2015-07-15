package com.uslive.rabyks.controllers.mvc;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.uslive.rabyks.models.mongo.ConfirmedReservation;
import com.uslive.rabyks.repositories.mongo.ConfirmedReservationsRepository;

@Controller
public class ReservationsController {

	private Logger log = LoggerFactory.getLogger(ReservationsController.class);

	@Autowired
	private ConfirmedReservationsRepository confResRepo;

	@RequestMapping(value="/reservationSuccess", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void reservationSuccess(HttpServletRequest request) {

		try {
			String partnerId = request.getParameter("partnerId");
			String objectId = request.getParameter("objectId");
			String type = request.getParameter("type");
			String dateOfReservation = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
			ConfirmedReservation cr = new ConfirmedReservation();
			cr.setPartnerId(partnerId);
			cr.setObjectId(objectId);
			cr.setType(type);
			cr.setDateOfReservation(dateOfReservation);
			confResRepo.save(cr);
		} catch (Exception e) {
			log.error("reservationSuccess error: ", e);
		}
	}
	
}
