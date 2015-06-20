package com.uslive.rabyks.controllers.mvc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uslive.rabyks.models.mysql.Partner;
import com.uslive.rabyks.repositories.mysql.PartnerRepository;

@Controller
public class PartnerController {

	Logger log = LoggerFactory.getLogger(PartnerController.class);

	@Autowired
	private PartnerRepository partnerRepo;
	
	@RequestMapping(value="/getPartner/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Partner getPartnerById(@PathVariable("id") Integer id) {
		
		Partner partner = null;
		try {
			partner = partnerRepo.findOne(id);
			return partner; 
		} catch (Exception e) {
			log.error("getPartnerById error: ", e.getMessage());
			return partner;
		}
	} 
	
	@RequestMapping(value="/getPartners", method=RequestMethod.GET)
	@ResponseBody
	public List<Partner> getPartners() {
		try {
			return partnerRepo.findAll();
		} catch (Exception e) {
			log.error("getPartners error: ", e.getMessage());
			return null;
		}
	}
	
	@RequestMapping(value="/getPartner/{name}", method=RequestMethod.GET)
	@ResponseBody
	public String getPartnerByName(@PathVariable("name") String name) {
		
		Partner partner = null;
		try {
			partner = partnerRepo.findByName(name);
			return partner.toString(); 
		} catch (Exception e) {
			log.error("getPartnerById error: ", e.getMessage());
			return partner.toString();
		}
	}
	
	@RequestMapping(value="/getLatestPartners", method=RequestMethod.GET)
	@ResponseBody
	public List<Partner> getLatestPartners(@RequestParam(value="timestamp") String createdAt) {
	
		try { 
			return partnerRepo.findByCreatedAtGreaterThan(Long.parseLong(createdAt));
		} catch (Exception e) {
			log.error("getLatestPartners error! ", e.getMessage());
			return null;
		}
	}
}
