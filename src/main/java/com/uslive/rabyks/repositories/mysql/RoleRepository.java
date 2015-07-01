package com.uslive.rabyks.repositories.mysql;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.uslive.rabyks.models.mysql.Role;

public interface RoleRepository extends JpaRepository<Role, Serializable>{

	@Query(value="select new Role(role, userId) from Role r where r.role = :role")
	public List<Role> findByRole(@Param("role") int role);
	
	@Query(value="select new Role(role, userId) from Role r where r.userId = :userId")
	public List<Role> findByUserId(@Param("userId") int userId);
}
