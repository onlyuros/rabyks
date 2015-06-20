package com.uslive.rabyks.models;

public class Rezervisan {

	private int partnerId;
	
	private int objectId;

	public Rezervisan() {
		
	}
	
	public Rezervisan(int partnerId, int objectId) {
		super();
		this.partnerId = partnerId;
		this.objectId = objectId;
	}

	public int getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}
}
