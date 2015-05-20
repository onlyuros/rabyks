package com.uslive.rabyks.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.uslive.rabyks.models.Greeting;
import com.uslive.rabyks.models.HelloMessage;

@Controller
public class GreetingController {

	@MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(3000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }

	
//    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//    public User greeting(HelloMessage message) throws Exception {
//        
//    	User user = new User();
//    	user.setEmail("uros@hp.com");
//    	user.setPassword("password");
//    	user.setRole(0);
//    	return user;
//    }
}
