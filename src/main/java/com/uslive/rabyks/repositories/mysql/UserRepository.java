package com.uslive.rabyks.repositories.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uslive.rabyks.models.mysql.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findOneByEmail(String email);

	public User findById(int id);

	public User findByEmail(String email);

	public User findByEmailAndPassword(String email, String password);
}
