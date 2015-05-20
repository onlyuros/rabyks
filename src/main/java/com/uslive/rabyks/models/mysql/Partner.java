package com.uslive.rabyks.models.mysql;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the partners database table.
 * 
 */
@Entity
@Table(name="partners")
@NamedQuery(name="Partner.findAll", query="SELECT p FROM Partner p")
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
	private String workingHours;
	private List<User> users;

	public Partner() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}


	@Column(name="galery_img_1_url")
	public String getGaleryImg1Url() {
		return this.galeryImg1Url;
	}

	public void setGaleryImg1Url(String galeryImg1Url) {
		this.galeryImg1Url = galeryImg1Url;
	}


	@Column(name="galery_img_2_url")
	public String getGaleryImg2Url() {
		return this.galeryImg2Url;
	}

	public void setGaleryImg2Url(String galeryImg2Url) {
		this.galeryImg2Url = galeryImg2Url;
	}


	@Column(name="galery_img_3_url")
	public String getGaleryImg3Url() {
		return this.galeryImg3Url;
	}

	public void setGaleryImg3Url(String galeryImg3Url) {
		this.galeryImg3Url = galeryImg3Url;
	}


	@Column(name="layout_img_url")
	public String getLayoutImgUrl() {
		return this.layoutImgUrl;
	}

	public void setLayoutImgUrl(String layoutImgUrl) {
		this.layoutImgUrl = layoutImgUrl;
	}


	@Column(name="logo_url")
	public String getLogoUrl() {
		return this.logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Column(name="working_hours")
	public String getWorkingHours() {
		return this.workingHours;
	}

	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}


	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="partners")
	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}