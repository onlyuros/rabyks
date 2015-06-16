package com.uslive.rabyks.controllers.mvc;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.uslive.rabyks.models.mongo.PartnerObjectSetup;
import com.uslive.rabyks.models.mongo.ReservationObject;
import com.uslive.rabyks.repositories.mongo.PartnerObjectSetupRepository;

@Controller
public class PartnerObjectSetupController {

	Logger log = LoggerFactory.getLogger(PartnerObjectSetupController.class);

	@Autowired
	private PartnerObjectSetupRepository posRepository;
	
	@RequestMapping(value="/getPartnerObjectSetup/{id}", method=RequestMethod.GET)
	@ResponseBody
	public PartnerObjectSetup getPOS(@RequestParam(value = "id", required = false, defaultValue = "0") int id) {

		PartnerObjectSetup pos = new PartnerObjectSetup();
		
		try {
			ReservationObject ro = new ReservationObject(1, "separe", 1, 5, true, 5, new int[]{1,1});
			List<ReservationObject> roL = new ArrayList<ReservationObject>();
			roL.add(ro);
			
			pos.setObjects(roL);
			pos.setPartnerId(1);
			pos.setDefaultBarseatSeatCount(1);
			pos.setDefaultSepareSeatCount(1);
			pos.setDefaultStandSeatCount(1);
			pos.setDefaultTableSeatCount(1);
			
			posRepository.save(pos);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return pos;
	}
	
	@RequestMapping(value="/postPartnerObjectSetup", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void postPOS() {
		
		
	}
}
