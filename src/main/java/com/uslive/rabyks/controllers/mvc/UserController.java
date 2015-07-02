package com.uslive.rabyks.controllers.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
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
import com.uslive.rabyks.services.UserService;

@Controller
public class UserController {

	private Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest request) {

		String json = null;
		try {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			json = userService.login(email, password);
		} catch (Exception e) {
			log.error("login error: ", e);
		}
		return json;
	}
	
	@RequestMapping(value="/createUser", method=RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public void createUser(@RequestBody User user) {

		try {
			userService.save(user);
		} catch (Exception e) {
			log.error("Create user error: ", e);
		}
	}
		
	@RequestMapping(value="/getUser/{email}", method=RequestMethod.GET) 
	@ResponseBody
	public User getUserByEmail(@PathVariable("email") String email) {
		
		User user = null;
		try {
			user = userService.findByEmail(email);
		} catch (Exception e) {
			log.error("Get user by email error: ", e);
		}
		return user;
	}
	
	@RequestMapping(value="/getUserRoles/{id}", method=RequestMethod.GET)
	@ResponseBody
	public String getUserRoles(@PathVariable("id") int id) {
		
		int r;
		String roleS = "";
		
		try {
			List<Role> roles = userService.findByUserId(id);
			for(Role role : roles) {
				r = role.getRole();
				if(r == 1) {
					roleS = "admin";
					break;
				} else if (r == 2) {
					roleS = "konobar";
				}
			}
			
			return roleS;
		} catch (Exception e) {
			log.error("Get User roles error: ", e);
			return roleS;
		}
	}
	
	@RequestMapping(value="/addWaiter", method=RequestMethod.POST) 
	@ResponseStatus(value=HttpStatus.OK)
	public void addWaiter(HttpServletRequest request) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		int partnerId = Integer.parseInt(request.getParameter("partnerId"));
		try {
			userService.addWaiter(email, password, partnerId);
		} catch (Exception e) {
			log.error("addWaiter error: ", e);
		}
	}
	
	@RequestMapping(value="/removeWaiter", method=RequestMethod.POST) 
	@ResponseStatus(value=HttpStatus.OK)
	public void removeWaiter(HttpServletRequest request) {
		String email = request.getParameter("email");
		try {
			Integer id = userService.deleteByEmail(email);
		} catch (Exception e) {
			log.error("removeWaiter error: ", e);
		}
	}
	
	@RequestMapping(value="/getWaiters/{partnerId}", method=RequestMethod.GET)
	@ResponseBody
	public List<User> getWaiters(@PathVariable("partnerId") String partnerId) {
	
		List<User> ul = null;
		try {
			ul = userService.findByRole(2);
		} catch (Exception e) {
			log.error("getWaiters error: ", e);
		}
		return ul;
	}
}
