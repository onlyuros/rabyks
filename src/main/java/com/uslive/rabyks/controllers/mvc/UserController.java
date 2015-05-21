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

import com.uslive.rabyks.common.Role;
import com.uslive.rabyks.models.UserCreateForm;
import com.uslive.rabyks.models.mysql.User;
import com.uslive.rabyks.service.user.UserService;

@Controller
public class UserController {

	private Logger log = LoggerFactory.getLogger("UserController.class");
		
	private final UserService userService;
	
	@Autowired
    public UserController(UserService userService) {
		 this.userService = userService;
    }

    @RequestMapping("/user/{id}")
    @ResponseBody
    public User getUser(@PathVariable Long id) {
    	User user = userService.getUserById(id);
    	if(user == null) {
    		log.error("No user with id: " + id + " found!");
    		return null;
    	}
    	return user;
    }

    // NEEDS A JSON AS SUCH { "email": "...", "password": "...", "number": "...", "role": "..." }
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public User createUser(@RequestParam String u) {
    	UserCreateForm ucf = new UserCreateForm();
    	try {
	    	JSONObject obj = new JSONObject(u);
	    	ucf.setPassword(obj.getString("password"));
	    	ucf.setEmail(obj.getString("email"));
	    	ucf.setNumber(obj.getString("number"));
	    	ucf.setRole(obj.getString("role").equals(Role.USER) ? Role.USER : Role.ADMIN);
	    	
	    	return userService.create(ucf);
    	}
    	catch (Exception e) {
    		log.error("Something went wrong! ", e);
    	}
    	return null;
    }
}
