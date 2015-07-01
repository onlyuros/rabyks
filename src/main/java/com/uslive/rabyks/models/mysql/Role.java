package com.uslive.rabyks.models.mysql;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the roles database table.
 * 
 */
@Entity
@Table(name="roles")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int role;
	private int userId;
	private User user;

	public Role() {
	}
	
	public Role(int role, int userId) {
		this.role = role;
		this.userId = userId;
	}
	
	public Role(int id, int role, int userId, User user) {
		super();
		this.id = id;
		this.role = role;
		this.userId = userId;
		this.user = user;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(nullable=false)
	public int getRole() {
		return this.role;
	}

	public void setRole(int role) {
		this.role = role;
	}


	@Column(name="user_id", nullable=false)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}


	//bi-directional one-to-one association to User
	@OneToOne
	@JoinColumn(name="id", nullable=false, insertable=false, updatable=false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}