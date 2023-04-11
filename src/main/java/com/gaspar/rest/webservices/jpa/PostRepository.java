package com.gaspar.rest.webservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gaspar.rest.webservices.user.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
