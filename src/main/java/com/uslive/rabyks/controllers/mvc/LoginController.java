package com.uslive.rabyks.controllers.mvc;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.uslive.rabyks.common.RoleEnum;
import com.uslive.rabyks.models.mysql.User;
import com.uslive.rabyks.repositories.mysql.RoleRepository;
import com.uslive.rabyks.repositories.mysql.UserRepository;

@Controller
public class LoginController {

	private Logger log = LoggerFactory.getLogger("LoginController.class");
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private RoleRepository roleRepository;
	
	@Autowired
	private Environment env;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void register(@RequestBody String user) {
		
		try {
			JSONObject jsonUser = new JSONObject(user);
			String username = jsonUser.getString("username");
			String email = jsonUser.getString("email");
			String number = jsonUser.getString("number");
			String password = jsonUser.getString("password");
			
			User userDB = new User();
			userDB.setPassword(password); // TODO SAVE ENCRYPTED
			userDB.setNumber(number);
			userDB.setEmail(email);
			userDB.setRole(roleRepository.findByRole(RoleEnum.USER.ordinal()));
			userRepository.save(userDB);
			
		} catch(Exception e) {
			log.error("Registration failed", e.getMessage());
		}
	}
}
