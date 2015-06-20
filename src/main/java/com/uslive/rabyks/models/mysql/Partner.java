package com.uslive.rabyks.models.mysql;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the partners database table.
 * 
 */
@Entity
@Table(name="partners")
public class Partner implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String address;
	private String details;
	private String galeryImg1Url;
	private String galeryImg2Url;
	private String galeryImg3Url;
	private String layoutImgUrl;
	private String logoUrl;
	private String name;
	private int type;
	private String workingHours;
	private Timestamp createdAt;
	private Timestamp modifiedAt;
	private List<User> users1;
	private List<User> users2;

	public Partner() {
	}

	public Partner(int id, String address, String details,
			String galeryImg1Url, String galeryImg2Url, String galeryImg3Url,
			String layoutImgUrl, String logoUrl, String name, int type,
			String workingHours) {
		this.id = id;
		this.address = address;
		this.details = details;
		this.galeryImg1Url = galeryImg1Url;
		this.galeryImg2Url = galeryImg2Url;
		this.galeryImg3Url = galeryImg3Url;
		this.layoutImgUrl = layoutImgUrl;
		this.logoUrl = logoUrl;
		this.name = name;
		this.type = type;
		this.workingHours = workingHours;
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


	@Column(nullable=false, length=100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	@Column(length=100)
	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}


	@Column(name="galery_img_1_url", length=100)
	public String getGaleryImg1Url() {
		return this.galeryImg1Url;
	}

	public void setGaleryImg1Url(String galeryImg1Url) {
		this.galeryImg1Url = galeryImg1Url;
	}


	@Column(name="galery_img_2_url", length=100)
	public String getGaleryImg2Url() {
		return this.galeryImg2Url;
	}

	public void setGaleryImg2Url(String galeryImg2Url) {
		this.galeryImg2Url = galeryImg2Url;
	}


	@Column(name="galery_img_3_url", length=100)
	public String getGaleryImg3Url() {
		return this.galeryImg3Url;
	}

	public void setGaleryImg3Url(String galeryImg3Url) {
		this.galeryImg3Url = galeryImg3Url;
	}


	@Column(name="layout_img_url", nullable=false, length=100)
	public String getLayoutImgUrl() {
		return this.layoutImgUrl;
	}

	public void setLayoutImgUrl(String layoutImgUrl) {
		this.layoutImgUrl = layoutImgUrl;
	}


	@Column(name="logo_url", nullable=false, length=100)
	public String getLogoUrl() {
		return this.logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}


	@Column(nullable=false, length=30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Column(nullable=false)
	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}


	@Column(name="working_hours", nullable=false, length=100)
	public String getWorkingHours() {
		return this.workingHours;
	}

	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}

	@Column(name="created_at", nullable=false)
	public Timestamp getCreatedAt()  {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	@Column(name="modified_at")
	public Timestamp getModifiedAt()  {
		return this.modifiedAt;
	}

	public void setModifiedAt(Timestamp modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	
	//bi-directional many-to-many association to User
	@ManyToMany
	@JoinTable(
		name="user_partner"
		, joinColumns={
			@JoinColumn(name="partner_id", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="user_id", nullable=false)
			}
		)
	public List<User> getUsers1() {
		return this.users1;
	}

	public void setUsers1(List<User> users1) {
		this.users1 = users1;
	}


	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="partners2")
	public List<User> getUsers2() {
		return this.users2;
	}

	public void setUsers2(List<User> users2) {
		this.users2 = users2;
	}
}