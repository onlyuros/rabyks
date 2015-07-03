package com.uslive.rabyks.repositories.mysql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uslive.rabyks.models.mysql.Role;
import com.uslive.rabyks.models.mysql.RolePK;

public interface RoleRepository extends JpaRepository<Role, RolePK>{

	public List<Role> findByIdRole(int role);
	
	public List<Role> findByIdUserId(int userId);
}
