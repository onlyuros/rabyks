package com.uslive.rabyks.repositories.mysql;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uslive.rabyks.models.mysql.User;

@Repository
public interface UserRepository extends JpaRepository<User, Serializable> {

}
