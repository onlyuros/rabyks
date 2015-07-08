package com.uslive.rabyks.repositories.mysql;
		
import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uslive.rabyks.models.mysql.Partner;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Integer>{
	
	@Query(value="select new Partner(id, name, address, number, logoUrl, layoutImgUrl, galeryImg1Url, galeryImg2Url, galeryImg3Url, details, workingHours, createdAt, modifiedAt, longitude, latitude) from Partner p where p.name = :name")
	public Partner findByName(@Param("name") String name);

	@Query(value="select new Partner(id, name, address, number, logoUrl, layoutImgUrl, galeryImg1Url, galeryImg2Url, galeryImg3Url, details, workingHours, createdAt, modifiedAt, longitude, latitude) from Partner")
	public List<Partner> findAll();
	
	@Query(value="select new Partner(id, name, address, number, logoUrl, layoutImgUrl, galeryImg1Url, galeryImg2Url, galeryImg3Url, details, workingHours, createdAt, modifiedAt, longitude, latitude) from Partner p where p.createdAt>:createdAt")
	public List<Partner> findByCreatedAtGreaterThan(@Param("createdAt") BigInteger createdAt);

	@Query(value="select new Partner(id, name, address, number, logoUrl, layoutImgUrl, galeryImg1Url, galeryImg2Url, galeryImg3Url, details, workingHours, createdAt, modifiedAt, longitude, latitude) from Partner p where p.id = :id")
	public Partner findById(@Param("id") int id);
}
