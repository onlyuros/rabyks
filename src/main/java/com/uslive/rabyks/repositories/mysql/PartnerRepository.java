package com.uslive.rabyks.repositories.mysql;
		
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uslive.rabyks.models.mysql.Partner;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Integer>{
	
	public Partner findByName(String name);
}
