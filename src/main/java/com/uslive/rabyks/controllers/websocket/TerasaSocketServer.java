package com.uslive.rabyks.controllers.websocket;

import java.net.ServerSocket;
import java.net.Socket;

import com.uslive.rabyks.common.SharedLists;

public class TerasaSocketServer extends Thread {
	
	int terasaPort = 4445;

	public void run() {

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
