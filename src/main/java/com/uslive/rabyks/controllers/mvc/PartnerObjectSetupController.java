package com.uslive.rabyks.controllers.mvc;

import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.uslive.rabyks.common.SharedLists;
import com.uslive.rabyks.models.ClubNameSocket;
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
			log.error("getPOS error: ", e);
			return null;
		}
	}
	
	@RequestMapping(value="/postPartnerObjectSetup", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void updatePartnerObjectSetup(@RequestBody PartnerObjectSetup pos) {
		try {
			
			PartnerObjectSetup posDB = posRepo.findByPartnerId(pos.getPartnerId());
//			posOld.setDefaultBarseatSeatCount(pos.getDefaultBarseatSeatCount());
//			posOld.setDefaultSepareSeatCount(pos.getDefaultSepareSeatCount());
//			posOld.setDefaultStandSeatCount(pos.getDefaultStandSeatCount());
//			posOld.setDefaultTableSeatCount(pos.getDefaultTableSeatCount());
			posDB.setObjects(pos.getObjects());
			posRepo.save(posDB); 
//			
//			synchronized(SharedLists.clubNameSocketList) {
//	    		System.out.println("USAO U SINH clubNameSocketList");
//	        	for(ClubNameSocket cns : SharedLists.clubNameSocketList) {
//	    				if(pos.getPartnerId() == cns.getPartnerId()) {
//		            		PrintWriter outA = new PrintWriter(cns.getSocket().getOutputStream(), true);
//		            		System.out.println("PISE NA SVE SOCKETE");
//		            		outA.println("poruka:" + "partnerObjectSetup");
//	    				}
//	        	}
//	    	}
		} catch (Exception e) {
			log.error("updatePartnerObjectSetup error! ", e);
		}
		
		
	}
}
