package com.uslive.rabyks.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String email;
	private String number;
	private String password;
	private int role;
	private List<Partner> partners;

	public User() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}


	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public int getRole() {
		return this.role;
	}

	public void setRole(int role) {
		this.role = role;
	}


	//bi-directional many-to-many association to Partner
	@ManyToMany
	@JoinTable(
		name="user_partner"
		, joinColumns={
			@JoinColumn(name="user_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="partner_id")
			}
		)
	public List<Partner> getPartners() {
		return this.partners;
	}

	public void setPartners(List<Partner> partners) {
		this.partners = partners;
	}

}