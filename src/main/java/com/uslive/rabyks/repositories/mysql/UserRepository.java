package com.uslive.rabyks.repositories.mysql;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uslive.rabyks.models.mysql.User;

public interface UserRepository extends JpaRepository<User, Serializable> {

	public User findOneByEmail(String email);

	public User findById(int id);

	public User findUserByEmail(String email);

	public User findByEmailAndPassword(String email, String password);
}
