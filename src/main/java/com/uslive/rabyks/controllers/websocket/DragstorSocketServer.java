package com.uslive.rabyks.controllers.websocket;

import java.net.ServerSocket;
import java.net.Socket;

import com.uslive.rabyks.common.SharedLists;

public class DragstorSocketServer extends Thread{

	private int dragstorPort = 4444;
	
	public void run() {

		try (ServerSocket serverSocket = new ServerSocket(dragstorPort)) {
			System.out.println("Pokrenut server na portu: " + dragstorPort);

			boolean listening = true;
			
			while(listening) {
				
				Socket clubSocket = serverSocket.accept();
				SharedLists.listaKonektovanihKlijenataDragstor.add(clubSocket);
	    		new ClubSocketThread(clubSocket).start();
	    	}
			
	    } catch (Exception e) {
	    	System.err.println("Server socket start error! Port: " + dragstorPort);
	    }
	}
}
