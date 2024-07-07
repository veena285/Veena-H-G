package com.example.imdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.imdb.Entity.User;

public interface UserRepository  extends JpaRepository<User, Integer>{
	
	@Query(" select E from User E where E.userEmail=?1")
	public User getUsetByEmail(String mail);

}
