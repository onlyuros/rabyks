package com.uslive.rabyks.models.mysql;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the roles database table.
 * 
 */
@Embeddable
public class RolePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int role;
	private int userId;

	public RolePK() {
	}

	@Column(unique=true, nullable=false)
	public int getRole() {
		return this.role;
	}
	public void setRole(int role) {
		this.role = role;
	}

	@Column(name="user_id", insertable=false, updatable=false, unique=true, nullable=false)
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RolePK)) {
			return false;
		}
		RolePK castOther = (RolePK)other;
		return 
			(this.role == castOther.role)
			&& (this.userId == castOther.userId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.role;
		hash = hash * prime + this.userId;
		
		return hash;
	}
}