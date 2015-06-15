package com.uslive.rabyks.controllers.websocket;

import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer extends Thread{

	public void run() {

		try (ServerSocket serverSocket = new ServerSocket(4444)) {
			System.out.println("Pokrenut server na portu: " + 4444);

			boolean listening = true;
			
			while(listening) {
				
				Socket clubSocket = serverSocket.accept();
	    		new ClubSocketThread(clubSocket).start();
	    	}
			
	    } catch (Exception e) {
	    	System.err.println("Server socket start error! Port: " + 4444);
	    }
	}
}
