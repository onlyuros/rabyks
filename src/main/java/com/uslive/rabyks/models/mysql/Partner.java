package com.uslive.rabyks.models.mysql;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigInteger;
import java.math.BigDecimal;
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
	private BigInteger createdAt;
	private String details;
	private String galeryImg1Url;
	private String galeryImg2Url;
	private String galeryImg3Url;
	private BigDecimal latitude;
	private String layoutImgUrl;
	private String logoUrl;
	private BigDecimal longitude;
	private BigInteger modifiedAt;
	private String name;
	private String number;
	private String workingHours;
	private List<User> users1;
	private List<User> users2;
	private List<Type> types;

	public Partner() {
	}

	public Partner(int id, String name, String address, String number, 
			String logoUrl, String layoutImgUrl, String galeryImg1Url, 
			String galeryImg2Url, String galeryImg3Url, String details, 
			String workingHours, BigInteger createdAt, BigInteger modifiedAt, 
			BigDecimal longitude, BigDecimal latitude) {
		super();
		this.id = id;
		this.address = address;
		this.createdAt = createdAt;
		this.details = details;
		this.galeryImg1Url = galeryImg1Url;
		this.galeryImg2Url = galeryImg2Url;
		this.galeryImg3Url = galeryImg3Url;
		this.latitude = latitude;
		this.layoutImgUrl = layoutImgUrl;
		this.logoUrl = logoUrl;
		this.longitude = longitude;
		this.modifiedAt = modifiedAt;
		this.name = name;
		this.number = number;
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


	@Column(name="created_at", nullable=false)
	public BigInteger getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(BigInteger createdAt) {
		this.createdAt = createdAt;
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


	@Column(nullable=false, precision=10, scale=8)
	public BigDecimal getLatitude() {
		return this.latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
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


	@Column(nullable=false, precision=11, scale=8)
	public BigDecimal getLongitude() {
		return this.longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}


	@Column(name="modified_at", nullable=false)
	public BigInteger getModifiedAt() {
		return this.modifiedAt;
	}

	public void setModifiedAt(BigInteger modifiedAt) {
		this.modifiedAt = modifiedAt;
	}


	@Column(nullable=false, length=30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Column(length=20)
	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}


	@Column(name="working_hours", nullable=false, length=100)
	public String getWorkingHours() {
		return this.workingHours;
	}

	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}


	//bi-directional many-to-many association to User
	@ManyToMany
	@JsonIgnore
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
	@JsonIgnore
	public List<User> getUsers2() {
		return this.users2;
	}

	public void setUsers2(List<User> users2) {
		this.users2 = users2;
	}


	//bi-directional many-to-many association to Type
	@ManyToMany
	@JoinTable(
		name="partner_type"
		, joinColumns={
			@JoinColumn(name="partner_id", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="type_id", nullable=false)
			}
		)
	public List<Type> getTypes() {
		return this.types;
	}

	public void setTypes(List<Type> types) {
		this.types = types;
	}

}