package com.uslive.rabyks.models;

public class Rezervisan {

	private String clubName;
	
	private int objectId;

	public Rezervisan() {
		
	}
	
	public Rezervisan(String clubName, int objectId) {
		super();
		this.clubName = clubName;
		this.objectId = objectId;
	}

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}
}
