package com.uslive.rabyks.controllers.mvc;

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

import com.uslive.rabyks.models.mysql.User;
import com.uslive.rabyks.repositories.mysql.UserRepository;

@Controller
public class UserController {

	private Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserRepository userRepo;
	
	@RequestMapping(value="/createUser", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void createUser(@RequestBody User user) {

		try {
			userRepo.save(user);
		} catch (Exception e) {
			log.error("createUser error: ", e.getMessage());
		}
	}
	
	
	@RequestMapping(value="/getUser/{email}", method=RequestMethod.GET) 
	@ResponseBody
	public User getUserByEmail(@PathVariable("email") String email) {
		
		User user = null;
		try {
			user = userRepo.findUserByEmail(email);
			return user;
		} catch (Exception e) {
			log.error("getUser error: ", e.getMessage());
			return user;
		}
	}
}
