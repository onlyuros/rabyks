package com.uslive.rabyks.models.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="partner_object_setup")
public class PartnerObjectSetup {

	@Id
    private String id;

    private String name;

    public PartnerObjectSetup(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
    	return "Partner object setup id: " + id + " name: " + name;
    }
}
