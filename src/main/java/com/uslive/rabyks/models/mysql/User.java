package com.uslive.rabyks.models.mysql;

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
	private List<Partner> partners1;
	private Role role;
	private List<Partner> partners2;

	public User() {
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


	@Column(nullable=false, length=40)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Column(length=20)
	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}


	@Column(nullable=false, length=20)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	//bi-directional many-to-many association to Partner
	@ManyToMany(mappedBy="users1")
	public List<Partner> getPartners1() {
		return this.partners1;
	}

	public void setPartners1(List<Partner> partners1) {
		this.partners1 = partners1;
	}


	//bi-directional one-to-one association to Role
	@OneToOne(mappedBy="user")
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


	//bi-directional many-to-many association to Partner
	@ManyToMany
	@JoinTable(
		name="user_partner"
		, joinColumns={
			@JoinColumn(name="user_id", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="partner_id", nullable=false)
			}
		)
	public List<Partner> getPartners2() {
		return this.partners2;
	}

	public void setPartners2(List<Partner> partners2) {
		this.partners2 = partners2;
	}

}