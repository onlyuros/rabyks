package com.uslive.rabyks.models.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="confirmed_reservations")
public class ConfirmedReservation {

	@Id
    private String id;
	
	private String partnerId;
	
	private String objectId;
	
	private String type;
	
	private String dateOfReservation;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDateOfReservation() {
		return dateOfReservation;
	}

	public void setDateOfReservation(String dateOfReservation) {
		this.dateOfReservation = dateOfReservation;
	}
}
