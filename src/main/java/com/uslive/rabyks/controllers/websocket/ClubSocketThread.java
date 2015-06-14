package com.uslive.rabyks.controllers.websocket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.uslive.rabyks.common.SharedLists;

@Component
public class ClubSocketThread extends Thread {

	private Socket socket = null;

	public ClubSocketThread() {
		
	}
	
    public ClubSocketThread(Socket socket) {
        super("ClubSocket");
        this.socket = socket;
    }

	public void run() {
		
		System.out.println("Pokrenut novi club socket");
		
		try (
	            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        ) {
	            String inputLine;
	            ArrayList<Socket> notifyAll = new ArrayList<Socket>();
	            int port = socket.getPort();
	            
	            while ((inputLine = in.readLine()) != null) {
	            	if (inputLine.equals("Bye"))
                        break;
	            	if (port == 4444) {
		            	synchronized(SharedLists.listaRezervisanihObjekataDragstor) {
		            		SharedLists.listaRezervisanihObjekataDragstor.add(Integer.parseInt(inputLine));
		            		notifyAll = SharedLists.listaKonektovanihKlijenataDragstor;
		            		
		            		for(Socket s : notifyAll) {
			            		PrintWriter outA = new PrintWriter(socket.getOutputStream(), true);
			            		outA.print(SharedLists.listaRezervisanihObjekataDragstor);
			            	}
		            	}
	            	} else if (port == 4445) {
	            		synchronized(SharedLists.listaRezervisanihObjekataTerasa) {
	            			SharedLists.listaRezervisanihObjekataTerasa.add(Integer.parseInt(inputLine));
	            			notifyAll = SharedLists.listaKonektovanihKlijenataTerasa;
	            			
	            			for(Socket s : notifyAll) {
	    	            		PrintWriter outA = new PrintWriter(socket.getOutputStream(), true);
	    	            		outA.print(SharedLists.listaRezervisanihObjekataTerasa);
	    	            	}
	            		}
            		}
	            	
	            }
	            socket.close();
	        } catch (Exception e) {
	        	System.err.println("Club socket error!");
	        }
	}
}
