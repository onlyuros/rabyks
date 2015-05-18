package com.uslive.rabyks.repositories;
		
import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uslive.rabyks.models.Partner;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Serializable>{

}
