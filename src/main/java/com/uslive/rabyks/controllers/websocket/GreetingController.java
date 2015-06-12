package com.uslive.rabyks.controllers.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.uslive.rabyks.models.Greeting;
import com.uslive.rabyks.models.HelloMessage;

@Controller
public class GreetingController {

	@MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(String message) throws Exception {
        return new Greeting("Hello, " + message + "!");
    }

}
