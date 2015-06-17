package com.uslive.rabyks.controllers.websocket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.uslive.rabyks.common.SharedLists;
import com.uslive.rabyks.models.Rezervisan;
import com.uslive.rabyks.models.mongo.Reservation;
import com.uslive.rabyks.repositories.mongo.ReservationRepositories;

@Component
@Scope("prototype")
public class ClubSocketThread extends Thread {

	private Socket socket = null;
	
	private static Logger log = LoggerFactory.getLogger(ClubSocketThread.class);
	
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
		
		try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter out = new PrintWriter(socket.getOutputStream(), true);) {

			String inputLine;
            
			while (true) {

            	inputLine = in.readLine();
            	String[] data = inputLine.split(":");
            	String command = data[0];
            	String club = data[1];
            	
            	if (command.equals("bye")) {
            		synchronized(SharedLists.clubNameSocketList) {
            			SharedLists.clubNameSocketList.remove(new ClubNameSocket(club, socket));
            		}
            		break;
            	}
            	
                else if (command.equals("club")) {
                	
                	out.println("DOBRODOSAO U KLUB TEBRA");
        			synchronized (SharedLists.clubNameSocketList) {
            			SharedLists.clubNameSocketList.add(new ClubNameSocket(club, socket));
        			}
        			
        			synchronized (SharedLists.listaRezervacija) {
						for(Rezervisan rzrv : SharedLists.listaRezervacija) {
							if(club.equals(rzrv.getClubName())) {
								out.println(rzrv.getObjectId());
							}
						}
					}
        		}
            	
                else if (command.equals("rezervacija")) {
                	
                	String objectId = data[2];
                	String personCount = data[3];
                	String timeOfReservation = data[4];
                	
                	synchronized(SharedLists.clubNameSocketList) {
                    	for(ClubNameSocket cns : SharedLists.clubNameSocketList) {
                				if(club.equals(cns.getClubName())) {
		    	            		PrintWriter outA = new PrintWriter(cns.getSocket().getOutputStream(), true);
		    	            		outA.println(objectId);
                				}
                    	}
                	}
                	
                	synchronized (SharedLists.listaRezervacija) {
						SharedLists.listaRezervacija.add(new Rezervisan(club, Integer.parseInt(objectId)));
					}
                	
                	Reservation res = new Reservation();
                	res.setPartnerId(club);
                	res.setObjectId(objectId);
                	res.setPersonCount(personCount);
                	res.setTimeOfReservation(timeOfReservation);
                	rr.save(res);
                }
            }
            socket.close();
        } catch (Exception e) {
        	log.error("ClubSocketThread error! ", e.getMessage());
        }
	}
}
