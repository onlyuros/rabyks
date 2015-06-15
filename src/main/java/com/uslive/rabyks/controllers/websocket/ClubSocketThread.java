package com.uslive.rabyks.controllers.websocket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uslive.rabyks.common.SharedLists;
import com.uslive.rabyks.models.mongo.Reservation;
import com.uslive.rabyks.repositories.mongo.ReservationRepositories;

@Component
public class ClubSocketThread extends Thread {

	private Socket socket = null;
	
	@Autowired
	private ReservationRepositories rr;
	
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
	            
	            // ucitaj input
	            while ((inputLine = in.readLine()) != null) {
	            	
	            	// podeli input na komandu i ime kluba
	            	String[] commandAndClub = inputLine.split(":");
	            	
	            	// ako kaze bye izbaci ga iz liste socketa za taj klub i zatvori konekciju
	            	if (commandAndClub[0].equals("bye")) {
	            		synchronized(SharedLists.clubNameSocketList) {
	            			SharedLists.clubNameSocketList.remove(new ClubNameSocket(commandAndClub[1], socket));
	            		}
	            		break;
	            	}
	            	// ako kaze klub dodaj ga u listu socketa za taj klub
                    else if (commandAndClub[0].equals("club")) {
		            	
                    	for(String club : SharedLists.clubList) {
		            		if(club.equals(commandAndClub[1])) {
		            			synchronized(SharedLists.clubNameSocketList) {
			            			SharedLists.clubNameSocketList.add(new ClubNameSocket(commandAndClub[1], socket));
		            			}
							}
	            		}
            		}
                	
	            	// ako kaze rezervacija (ili nije jedna od prethodnih komandi) dodaj u listu rezervacija za taj klub i obavesti sve ostale u tom roomu o rezervaciji
                    else if (commandAndClub[0].equals("rezervacija")) {
                    	
                    	Reservation res = new Reservation();
                    	res.setPartnerId(commandAndClub[1]);
                    	res.setObjectId(commandAndClub[2]);
                    	res.setPersonCount(commandAndClub[3]);
                    	res.setTimeOfReservation(commandAndClub[4]);
                    	rr.save(res);
                    	
                    	for(ClubNameSocket cns : SharedLists.clubNameSocketList) {
                				if(commandAndClub[1].equals(cns.getClubName())) {
		    	            		PrintWriter outA = new PrintWriter(cns.getSocket().getOutputStream(), true);
		    	            		outA.print(commandAndClub[2]);
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
