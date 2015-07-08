package com.uslive.rabyks.controllers.mvc;

import java.math.BigInteger;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uslive.rabyks.models.mysql.Partner;
import com.uslive.rabyks.models.mysql.Type;
import com.uslive.rabyks.repositories.mysql.PartnerRepository;
import com.uslive.rabyks.repositories.mysql.TypeRepository;

@Controller
public class PartnerController {

	Logger log = LoggerFactory.getLogger(PartnerController.class);

	@Autowired
	private PartnerRepository partnerRepo;
	
	@Autowired
	private TypeRepository typeRepo;
	
	@RequestMapping(value="/getPartner/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Partner getPartnerById(@PathVariable("id") Integer id) {
		
		Partner partner = null;
		try {
			partner = partnerRepo.findOne(id);
			return partner; 
		} catch (Exception e) {
			log.error("getPartnerById error: ", e);
			return partner;
		}
	} 
	
	@RequestMapping(value="/getPartner/{name}", method=RequestMethod.GET)
	@ResponseBody
	public Partner getPartnerByName(@PathVariable("name") String name) {
		
		try {
			return partnerRepo.findByName(name);
		} catch (Exception e) {
			log.error("getPartnerById error: ", e);
			return null;
		}
	}
	
	@RequestMapping(value="/getPartners", method=RequestMethod.GET)
	@ResponseBody
	public List<Partner> getPartners() {
		try {
			return partnerRepo.findAll();
		} catch (Exception e) {
			log.error("getPartners error: ", e);
			return null;
		}
	}

	@RequestMapping(value="/getLatestPartners/{createdAt}", method=RequestMethod.GET)
	@ResponseBody
	public List<Partner> getLatestPartners(@PathVariable("createdAt") BigInteger createdAt) {
		try { 
			return partnerRepo.findByCreatedAtGreaterThan(createdAt);
		} catch (Exception e) {
			log.error("getLatestPartners error: ", e);
			return null;
		}
	}
	
	@RequestMapping(value="/getTypes", method=RequestMethod.GET)
	@ResponseBody
	public List<Type> getTypes() {
		List<Type> typeList = null;
		try {
			typeList = typeRepo.findAll();
		} catch (Exception e) {
			log.error("getTypes error", e);
		}
		return typeList;
	}
}
