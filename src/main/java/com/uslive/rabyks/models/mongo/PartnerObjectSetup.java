package com.uslive.rabyks.models.mongo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import com.uslive.rabyks.models.mongo.Objects;

@Document(collection="partner_object_setup")
public class PartnerObjectSetup {

	@Id
    private String id;

	@Indexed(unique=true)
    private int partnerId;

    private int defaultTableSeatCount;
    
    private int defaultSepareSeatCount;
    
    private int defaultBarseatSeatCount;
    
    private int defaultStandSeatCount;
    
    List<Objects> objects;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public int getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}

	public int getDefaultTableSeatCount() {
		return defaultTableSeatCount;
	}
	public void setDefaultTableSeatCount(int defaultTableSeatCount) {
		this.defaultTableSeatCount = defaultTableSeatCount;
	}

	public int getDefaultSepareSeatCount() {
		return defaultSepareSeatCount;
	}
	public void setDefaultSepareSeatCount(int defaultSepareSeatCount) {
		this.defaultSepareSeatCount = defaultSepareSeatCount;
	}

	public int getDefaultBarseatSeatCount() {
		return defaultBarseatSeatCount;
	}
	public void setDefaultBarseatSeatCount(int defaultBarseatSeatCount) {
		this.defaultBarseatSeatCount = defaultBarseatSeatCount;
	}

	public int getDefaultStandSeatCount() {
		return defaultStandSeatCount;
	}
	public void setDefaultStandSeatCount(int defaultStandSeatCount) {
		this.defaultStandSeatCount = defaultStandSeatCount;
	}

	public List<Objects> getObjects() {
		return objects;
	}
	public void setObjects(List<Objects> objects) {
		this.objects = objects;
	}
	@Override
	public String toString() {
		return "PartnerObjectSetup [id=" + id + ", partnerId=" + partnerId
				+ ", defaultTableSeatCount=" + defaultTableSeatCount
				+ ", defaultSepareSeatCount=" + defaultSepareSeatCount
				+ ", defaultBarseatSeatCount=" + defaultBarseatSeatCount
				+ ", defaultStandSeatCount=" + defaultStandSeatCount
				+ ", objects=" + objects + "]";
	}
}
