package com.uslive.rabyks.controllers.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

	private Logger log = LoggerFactory.getLogger("LoginController.class");
	
	@RequestMapping("/login")
    @ResponseBody
    public String login() {
		
		return "logged in";
	}
	
	@RequestMapping("/logout")
    @ResponseBody
    public String logout() {
		
		return "logged out";
	}
	
	@RequestMapping("/loginerror")
    @ResponseBody
    public String loginerror() {
		
		return "login error";
	}
	
}
