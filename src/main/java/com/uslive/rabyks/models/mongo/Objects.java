package com.uslive.rabyks.models.mongo;





public class Objects {

	private int objectId;
	private String type; // separe sto...
	private int timeOut; // koliko vazi rezervacija za ovaj objekat
	private float price;
	private boolean availability;
	private int numberOfSeats;
	private int coordinateX;
	private int coordinateY;
	
	public Objects() {}
	
	public Objects(int objectId, String type, int timeOut,
			float price, boolean availability, int numberOfSeats,
			int coordinateX, int coordinateY) {
		super();
		this.objectId = objectId;
		this.type = type;
		this.timeOut = timeOut;
		this.price = price;
		this.availability = availability;
		this.numberOfSeats = numberOfSeats;
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
	}
	
	public int getObjectId() {
		return objectId;
	}
	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public boolean isAvailability() {
		return availability;
	}
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	public int getNumberOfSeats() {
		return numberOfSeats;
	}
	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
	public int getCoordinateX() {
		return coordinateX;
	}
	public void setCoordinateX(int coordinateX) {
		this.coordinateX = coordinateX;
	}
	public int getCoordinateY() {
		return coordinateY;
	}
	public void setCoordinateY(int coordinateY) {
		this.coordinateY = coordinateY;
	}
}
