package com.uslive.rabyks.repositories.mysql;
		
import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uslive.rabyks.models.mysql.Partner;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Integer>{
	
	public Partner findByName(String name);

	public List<Partner> findAll();
	
	public List<Partner> findByCreatedAtGreaterThan(BigInteger createdAt);

	public Partner findById(int id);
}
