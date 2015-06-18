package com.uslive.rabyks.repositories.mysql;
		
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uslive.rabyks.models.mysql.Partner;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Integer>{
	
	public Partner findByName(String name);

	@Query(value="select new Partner(id, address, details, galeryImg1Url, galeryImg2Url, galeryImg3Url, layoutImgUrl, logoUrl, name, type, workingHours) from Partner")
	public List<Partner> findAll();
}
