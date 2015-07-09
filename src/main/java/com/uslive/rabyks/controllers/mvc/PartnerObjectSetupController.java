package com.uslive.rabyks.controllers.mvc;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
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
import com.uslive.rabyks.models.mongo.Objects;
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
			// nadji POS u bazi
			PartnerObjectSetup posDB = posRepo.findByPartnerId(pos.getPartnerId());
//			posOld.setDefaultBarseatSeatCount(pos.getDefaultBarseatSeatCount());
//			posOld.setDefaultSepareSeatCount(pos.getDefaultSepareSeatCount());
//			posOld.setDefaultStandSeatCount(pos.getDefaultStandSeatCount());
//			posOld.setDefaultTableSeatCount(pos.getDefaultTableSeatCount());
			
			List<Objects> objL = pos.getObjects();
			if(posDB != null) {
				posDB.setObjects(objL);
				posRepo.save(posDB); 
				
				JSONArray objA = new JSONArray(objL);
				
				synchronized(SharedLists.clubNameSocketList) {
		    		System.out.println("USAO U SINH clubNameSocketList");
		        	for(ClubNameSocket cns : SharedLists.clubNameSocketList) {
		    				if(pos.getPartnerId() == cns.getPartnerId()) {
			            		PrintWriter outA = new PrintWriter(cns.getSocket().getOutputStream(), true);
			            		System.out.println("PISE NA SVE SOCKETE");
			            		outA.println("partnerObjectSetup:" + objA.toString());
		    				}
		        	}
		    	}
			} else {
				PartnerObjectSetup posDBNew = new PartnerObjectSetup();
				posDBNew.setPartnerId(pos.getPartnerId());
				posDBNew.setDefaultBarseatSeatCount(pos.getDefaultBarseatSeatCount());
				posDBNew.setDefaultSepareSeatCount(pos.getDefaultSepareSeatCount());
				posDBNew.setDefaultStandSeatCount(pos.getDefaultStandSeatCount());
				posDBNew.setDefaultTableSeatCount(pos.getDefaultTableSeatCount());
				posDBNew.setObjects(objL);
				posRepo.save(posDB); 
				
				JSONArray objA = new JSONArray(objL);
				
				synchronized(SharedLists.clubNameSocketList) {
		    		System.out.println("USAO U SINH clubNameSocketList");
		        	for(ClubNameSocket cns : SharedLists.clubNameSocketList) {
		    				if(pos.getPartnerId() == cns.getPartnerId()) {
			            		PrintWriter outA = new PrintWriter(cns.getSocket().getOutputStream(), true);
			            		System.out.println("PISE NA SVE SOCKETE");
			            		outA.println("partnerObjectSetup:" + objA.toString());
		    				}
		        	}
		    	}
			}
		} catch (Exception e) {
			log.error("updatePartnerObjectSetup error! ", e);
		}
		
		
	}
}
