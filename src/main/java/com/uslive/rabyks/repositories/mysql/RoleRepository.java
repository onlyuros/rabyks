package com.uslive.rabyks.repositories.mysql;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uslive.rabyks.models.mysql.Role;

public interface RoleRepository extends JpaRepository<Role, Serializable>{

	public Role findByRole(int role);
}
