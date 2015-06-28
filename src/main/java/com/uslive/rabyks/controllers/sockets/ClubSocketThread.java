package com.uslive.rabyks.controllers.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.uslive.rabyks.common.SharedLists;
import com.uslive.rabyks.models.ClubNameSocket;
import com.uslive.rabyks.models.Rezervisan;
import com.uslive.rabyks.models.mongo.Objects;
import com.uslive.rabyks.models.mongo.PartnerObjectSetup;
import com.uslive.rabyks.models.mongo.Reservation;
import com.uslive.rabyks.repositories.mongo.PartnerObjectSetupRepository;
import com.uslive.rabyks.repositories.mongo.ReservationsRepository;

@Component
@Scope("prototype")
public class ClubSocketThread extends Thread {

	private Socket socket = null;
	
	private static Logger log = LoggerFactory.getLogger(ClubSocketThread.class);
	
	@Autowired
	private ReservationsRepository rr;
	
	@Autowired
	private PartnerObjectSetupRepository posRepo;
	
	public ClubSocketThread() {
		
	}
	
    public ClubSocketThread(Socket socket) {
        super("ClubSocket");
        this.socket = socket;
    }

	public void run() {
		
		System.out.println("Pokrenut novi club socket");
		
		try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
			
			String inputLine;
            
			while (true) {

            	inputLine = in.readLine();
            	String[] data = inputLine.split(":");
            	String command = data[0];
            	int partnerId = Integer.parseInt(data[1]);
            	
            	if (command.equals("bye")) {
            		synchronized(SharedLists.clubNameSocketList) {
            			SharedLists.clubNameSocketList.remove(new ClubNameSocket(partnerId, socket));
            		}
            		break;
            	}
            	
                else if (command.equals("hi")) {
                	
                	out.println("DOBRODOSAO U KLUB TEBRA");
                	PartnerObjectSetup pos = posRepo.findByPartnerId(partnerId);
        			synchronized (SharedLists.clubNameSocketList) {
            			SharedLists.clubNameSocketList.add(new ClubNameSocket(partnerId, socket));
        			}
        			
        			List<Integer> rez = new ArrayList<Integer>();
        			List<Objects> sviObjZaTajClub = pos.getObjects();
        			JSONObject objekatZaSlanje;
        			JSONArray nizObjekataZaSlanje = new JSONArray();
					
        			synchronized (SharedLists.listaRezervacija) {
						for(Rezervisan rzrv : SharedLists.listaRezervacija) {
							if(partnerId == rzrv.getPartnerId()) {
								rez.add(rzrv.getObjectId());
								//out.println(rzrv.getObjectId());
							}
						}
						for(Objects obj : sviObjZaTajClub) {
							if(rez.contains(obj.getObjectId())) {
								obj.setAvailability(false);
							}
							objekatZaSlanje = new JSONObject();
							objekatZaSlanje.put("objectId", obj.getObjectId());
							objekatZaSlanje.put("type", obj.getType());
							objekatZaSlanje.put("timeOut", obj.getTimeOut());
							objekatZaSlanje.put("price", obj.getPrice());
							objekatZaSlanje.put("availability", obj.isAvailability());
							objekatZaSlanje.put("numberOfSeats", obj.getNumberOfSeats());
							objekatZaSlanje.put("coordinateX", obj.getCoordinateX());
							objekatZaSlanje.put("coordinateY", obj.getCoordinateY());
							nizObjekataZaSlanje.put(objekatZaSlanje);
						}
						out.println(nizObjekataZaSlanje);
					}
        		}
            	
                else if (command.equals("rezervacija")) {
                	
                	String objectId = data[2];
                	String personCount = data[3];
                	String timeOfReservation = data[4];
                	
                	synchronized(SharedLists.clubNameSocketList) {
                    	for(ClubNameSocket cns : SharedLists.clubNameSocketList) {
                				if(partnerId == cns.getPartnerId()) {
                					System.out.println("PISES SVIMA: " + objectId);
		    	            		PrintWriter outA = new PrintWriter(cns.getSocket().getOutputStream(), true);
		    	            		outA.println("rezervacija:" + objectId);
                				}
                    	}
                	}
                	
                	synchronized (SharedLists.listaRezervacija) {
						SharedLists.listaRezervacija.add(new Rezervisan(partnerId, Integer.parseInt(objectId)));
					}
                	
                	Reservation res = new Reservation();
                	res.setPartnerId(partnerId+"");
                	res.setObjectId(objectId);
                	res.setPersonCount(personCount);
                	res.setTimeOfReservation(timeOfReservation);
                	rr.save(res);
                }
            	
                else if (command.equals("oslobodi")) {
                	System.out.println("USAO U OSLOBODI");
                	int objectId = Integer.valueOf(data[2]);
                	
                	synchronized (SharedLists.listaRezervacija) {
                		System.out.println("USAO U SINH listRezervacija");
                		
                		for(Iterator<Rezervisan> it = SharedLists.listaRezervacija.iterator(); it.hasNext();) {
            				Rezervisan rez = it.next();
            				if(rez.getPartnerId() == partnerId && rez.getObjectId() == objectId) {
            					SharedLists.listaRezervacija.remove(rez);
            					System.out.println("OSLOBODIO REZERVACIJU");
            				}
                		}
                		
//                		for(Rezervisan rez : SharedLists.listaRezervacija) {
//	                		if(rez.getPartnerId() == partnerId && rez.getObjectId() == objectId) {
//	                			System.out.println("OSLOBODIO REZERVACIJU");
//	                			SharedLists.listaRezervacija.remove(rez);
//	                		}
//                		}
					}
                	
                	synchronized(SharedLists.clubNameSocketList) {
                		System.out.println("USAO U SINH clubNameSocketList");
                    	for(ClubNameSocket cns : SharedLists.clubNameSocketList) {
                				if(partnerId == cns.getPartnerId()) {
		    	            		PrintWriter outA = new PrintWriter(cns.getSocket().getOutputStream(), true);
		    	            		System.out.println("PISE NA SVE SOCKETE");
		    	            		outA.println("oslobodi:" + objectId);
                				}
                    	}
                	}
                }
            }
			out.println("bye");
        } catch (IOException e) {
        	log.error("ClubSocketThread IOException! ", e);
        } catch (Exception e) {
        	log.error("ClubSocketThread Exception! ", e);
        } finally {
        	try {
	        	socket.close();  
        	} catch (Exception e) {
        		log.error("ClubSocketThread socket close error! ", e.getMessage());
        	}
        }
	}
}
