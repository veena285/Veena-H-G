package com.example.imdb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.imdb.Entity.Movie;
import com.example.imdb.Entity.User;
import com.example.imdb.enums.Genre;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
	
	@Query(" select m from Movie m where m.movieName=?1")
	public List<Movie> findByName(String name);
	
	
	@Query(" select m from Movie m where m.genre=?1")
	public List<Movie> findByGenre( Genre genreName);
	

}
