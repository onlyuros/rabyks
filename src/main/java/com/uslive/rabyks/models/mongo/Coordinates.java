package com.uslive.rabyks.models.mongo;

public class Coordinates {

	public int x;
	public int y;
	
	public Coordinates() {}
	
	public Coordinates(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
