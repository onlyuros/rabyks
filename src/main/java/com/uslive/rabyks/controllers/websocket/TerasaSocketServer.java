package com.uslive.rabyks.controllers.websocket;

import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.uslive.rabyks.common.SharedLists;

@Component
public class TerasaSocketServer implements CommandLineRunner {

	@Value("${terasa.port}")
	private int terasaPort;
	
	@Override
	public void run(String... args) throws Exception {

		try (ServerSocket serverSocket = new ServerSocket(terasaPort)) {
			System.out.println("Pokrenut server na portu: " + terasaPort);

			boolean listening = true;
			
			while(listening) {
				
				Socket clubSocket = serverSocket.accept();
				SharedLists.listaKonektovanihKlijenataTerasa.add(clubSocket);
	    		new ClubSocketThread(clubSocket).start();
	    	}
			
	    } catch (Exception e) {
	    	System.err.println("Server socket start error! Port: " + terasaPort);
	    }
	}
}
