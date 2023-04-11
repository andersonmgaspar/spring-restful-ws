package com.gaspar.rest.webservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gaspar.rest.webservices.user.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
