package com.uslive.rabyks.controllers.mvc;

import java.util.List;

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

import com.uslive.rabyks.models.mysql.Role;
import com.uslive.rabyks.models.mysql.User;
import com.uslive.rabyks.repositories.mysql.RoleRepository;
import com.uslive.rabyks.repositories.mysql.UserRepository;

@Controller
public class UserController {

	private Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
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
	
	@RequestMapping(value="/getUserRoles/{id}", method=RequestMethod.GET)
	@ResponseBody
	public String getUserRoles(@PathVariable("id") int id) {
		
		int r;
		String roleS = "";
		
		try {
			List<Role> roles = roleRepo.findByUserId(id);
			for(Role role : roles) {
				r = role.getRole();
				if(r == 3) {
					roleS = "admin";
					break;
				} else if (r == 2) {
					roleS = "konobar";
				}
			}
			
			return roleS;
		} catch (Exception e) {
			log.error("getUserById error: ", e);
			return roleS;
		}
	}
}
