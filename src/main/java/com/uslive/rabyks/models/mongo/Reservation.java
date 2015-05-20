package com.uslive.rabyks.models.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="reservation")
public class Reservation {

	@Id
    private String id;
	
	private String partnerId;
	
	private String objectId;
	
	private String personCount;
	
	private String timeOfReservation;

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

	public String getPersonCount() {
		return personCount;
	}

	public void setPersonCount(String personCount) {
		this.personCount = personCount;
	}

	public String getTimeOfReservation() {
		return timeOfReservation;
	}

	public void setTimeOfReservation(String timeOfReservation) {
		this.timeOfReservation = timeOfReservation;
	}
}
