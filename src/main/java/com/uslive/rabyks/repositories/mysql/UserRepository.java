package com.uslive.rabyks.repositories.mysql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uslive.rabyks.models.mysql.Partner;
import com.uslive.rabyks.models.mysql.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findOneByEmail(String email);

	public User findById(int id);

	public User findByEmail(String email);

	public User findByEmailAndPassword(String email, String password);
	
	@Query(value="SELECT u.partners1 FROM User u WHERE u.id = :userId")
	public List<Partner> findPartnersByUserId(@Param("userId") int userId);
	
	public Integer deleteByEmail(String email);
	
	public List<User> findByRole(int role);
}
