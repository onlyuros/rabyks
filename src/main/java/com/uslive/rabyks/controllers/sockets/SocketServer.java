package com.uslive.rabyks.controllers.sockets;

import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SocketServer implements CommandLineRunner{

	private static Logger log = LoggerFactory.getLogger(SocketServer.class);
	@Autowired
	private ApplicationContext context;
	
	@Override
	public void run(String... args) throws Exception  {

		try (ServerSocket serverSocket = new ServerSocket(4444)) {
			System.out.println("Pokrenut server na portu: " + 4444);

			boolean listening = true;
			
			while(listening) {
				
				Socket partnerSocket = serverSocket.accept();
				PartnerSocketThread pst = (PartnerSocketThread) context.getBean("partnerSocketThread", partnerSocket);
	    		pst.start();
	    	}
			
	    } catch (Exception e) {
	    	log.error("Server socket error: ", e);
	    }
	}
}
