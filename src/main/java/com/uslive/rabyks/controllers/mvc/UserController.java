package com.uslive.rabyks.controllers.mvc;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uslive.rabyks.common.RoleEnum;
import com.uslive.rabyks.models.mongo.PartnerObjectSetup;
import com.uslive.rabyks.models.mysql.Role;
import com.uslive.rabyks.models.mysql.User;
import com.uslive.rabyks.repositories.mongo.PartnerObjectSetupRepository;
import com.uslive.rabyks.repositories.mysql.UserRepository;

@Controller
public class UserController {

	private Logger log = LoggerFactory.getLogger("UserController.class");
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PartnerObjectSetupRepository posr;
	
    @RequestMapping("/user/{id}")
    @ResponseBody
    public User getUser(@PathVariable int id) {
    	
    	User user = null;
    	
    	try {
    		user = userRepo.findById(id);
    	} catch (Exception e) {
    		log.error("EXCEPTION!", e);
    	}
    	if(user == null) {
    		log.error("No user with id: " + id + " found!");
    		return null;
    	}
    	return user;
    }
    
    @RequestMapping(value = "/user/createone")
    @ResponseBody
    public String create() {
    	
    	PartnerObjectSetup pos = new PartnerObjectSetup("urosTEST");
    	
    	pos = posr.save(pos);
    	
    	return "napravljen! " + pos;
    }
    

    // NEEDS A JSON AS SUCH { "email": "...", "password": "...", "number": "...", "role": "..." }
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public User createUser(@RequestParam String u) {

    	User user = new User();
    	try {
	    	JSONObject obj = new JSONObject(u);
	    	user.setPassword(obj.getString("password"));
	    	user.setEmail(obj.getString("email"));
	    	user.setNumber(obj.getString("number"));
	    	Role role = new Role();
	    	role.setRole(Integer.parseInt(obj.getString("role")));
	    	user.setRole(role);
	    	
	    	return userRepo.save(user);
    	}
    	catch (Exception e) {
    		log.error("Something went wrong! ", e);
    	}
    	return null;
    }
}
