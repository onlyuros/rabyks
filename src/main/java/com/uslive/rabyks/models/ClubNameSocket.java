package com.uslive.rabyks.models;

import java.net.Socket;

public class ClubNameSocket {
	
	private int partnerId;
	
	private Socket socket;
	
	public ClubNameSocket() {
		
	}

	public ClubNameSocket(int partnerId, Socket socket) {
		super();
		this.partnerId = partnerId;
		this.socket = socket;
	}

	public int getPartnerId() {
		return partnerId;
	}

	public void setClubName(int partnerId) {
		this.partnerId = partnerId;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public String toString() {
		return "[" + getPartnerId() + ", " + getSocket().getPort() + " ]";
	}
}
