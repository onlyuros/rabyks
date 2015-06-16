package com.uslive.rabyks.controllers.websocket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.uslive.rabyks.common.SharedLists;
import com.uslive.rabyks.models.mongo.Reservation;
import com.uslive.rabyks.repositories.mongo.ReservationRepositories;

@Component
@Scope("prototype")
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
	            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	        ) {
	            String inputLine;
	            while (true) {
	            	System.out.println("u while cekam readline");
	            	inputLine = in.readLine();
	            	
	            	System.out.println("prosao readline " + inputLine);
	            	
	            	// podeli input na komandu i ime kluba
	            	String[] commandAndClub = inputLine.split(":");
	            	
	            	// ako kaze bye izbaci ga iz liste socketa za taj klub i zatvori konekciju
	            	if (commandAndClub[0].equals("bye")) {
	            		System.out.println("u bye");
	            		synchronized(SharedLists.clubNameSocketList) {
	            			System.out.println("u bye sync");
	            			SharedLists.clubNameSocketList.remove(new ClubNameSocket(commandAndClub[1], socket));
	            		}
	            		break;
	            	}
	            	// ako kaze klub dodaj ga u listu socketa za taj klub
                    else if (commandAndClub[0].equals("club")) {
                    	System.out.println("u club");
                    	out.println("DOBRODOSAO U KLUB TEBRA");
                    	for(String club : SharedLists.clubList) {
                    		System.out.println("u club for");
		            		if(club.equals(commandAndClub[1])) {
		            			synchronized(SharedLists.clubNameSocketList) {
		            				System.out.println("u club sync");
			            			SharedLists.clubNameSocketList.add(new ClubNameSocket(commandAndClub[1], socket));
			            			
			            			System.out.println(SharedLists.clubNameSocketList.toString());
		            			}
							}
	            		}
            		}
                	
	            	// ako kaze rezervacija (ili nije jedna od prethodnih komandi) dodaj u listu rezervacija za taj klub i obavesti sve ostale u tom roomu o rezervaciji
                    else if (commandAndClub[0].equals("rezervacija")) {
                    	System.out.println("u rezervacija");
                    	Reservation res = new Reservation();
                    	res.setId("1");
                    	res.setPartnerId(commandAndClub[1]);
                    	res.setObjectId(commandAndClub[2]);
                    	res.setPersonCount(commandAndClub[3]);
                    	res.setTimeOfReservation(commandAndClub[4]);
                    	rr.save(res);
                    	System.out.println("sacuvao");
                    	synchronized(SharedLists.clubNameSocketList) {
	                    	for(ClubNameSocket cns : SharedLists.clubNameSocketList) {
	                				if(commandAndClub[1].equals(cns.getClubName())) {
	                					System.out.println("u salji svima " + cns.toString());
			    	            		PrintWriter outA = new PrintWriter(cns.getSocket().getOutputStream(), true);
			    	            		outA.println(commandAndClub[2]);
			    	            		outA.flush();
	                				}
	                    	}
                    	}
                    }
	            }
	            socket.close();
	            System.out.println("ugasen server");
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	}
}
