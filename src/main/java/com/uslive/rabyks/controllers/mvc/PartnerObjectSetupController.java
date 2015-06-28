package com.uslive.rabyks.controllers.mvc;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.uslive.rabyks.models.mongo.PartnerObjectSetup;
import com.uslive.rabyks.repositories.mongo.PartnerObjectSetupRepository;
import com.uslive.rabyks.repositories.mysql.PartnerRepository;

@Controller
public class PartnerObjectSetupController {

	private Logger log = LoggerFactory.getLogger(PartnerObjectSetupController.class);

	@Autowired
	private PartnerObjectSetupRepository posRepo;
	
	@Autowired
	private PartnerRepository partnerRepo;
	
	@RequestMapping(value="/getPartnerObjectSetup/{partnerId}", method=RequestMethod.GET)
	@ResponseBody
	public PartnerObjectSetup getPOS(@PathVariable(value = "partnerId") int partnerId) {

		try {
			return posRepo.findByPartnerId(partnerId);
		} catch (Exception e) {
			log.error("getPOS error: ", e.getMessage());
			return null;
		}
	}
	
	@RequestMapping(value="/postPartnerObjectSetup", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void updatePartnerObjectSetup(HttpServletRequest request) {
		try {
			PartnerObjectSetup pos = (PartnerObjectSetup) request.getAttribute("partnerObjectSetup");
			PartnerObjectSetup posOld = posRepo.findByPartnerId(pos.getPartnerId());
			posOld.setDefaultBarseatSeatCount(pos.getDefaultBarseatSeatCount());
			posOld.setDefaultSepareSeatCount(pos.getDefaultSepareSeatCount());
			posOld.setDefaultStandSeatCount(pos.getDefaultStandSeatCount());
			posOld.setDefaultTableSeatCount(pos.getDefaultTableSeatCount());
			posOld.setObjects(pos.getObjects());
			posRepo.save(posOld); 
		} catch (Exception e) {
			log.error("updatePartnerObjectSetup error! ", e.getMessage());
		}
	}
}
