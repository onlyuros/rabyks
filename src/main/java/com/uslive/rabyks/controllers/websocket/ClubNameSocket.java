package com.uslive.rabyks.controllers.websocket;

import java.net.Socket;

public class ClubNameSocket {
	
	private String clubName;
	
	private Socket socket;
	
	public ClubNameSocket() {
		
	}

	public ClubNameSocket(String clubName, Socket socket) {
		super();
		this.clubName = clubName;
		this.socket = socket;
	}

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}
