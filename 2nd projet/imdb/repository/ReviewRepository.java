package com.example.imdb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.imdb.Entity.Review;


public interface ReviewRepository extends JpaRepository<Review, Integer> {
	
	
	@Query("select AVG(r.rating) from Review r where r.movieMap.movieId=?1")
	public float avgRating(int m);
	
	@Query("select r from Review r where r.movieMap.movieId=?1")
	public List<Review> findByMovieId(int movieId);
	
	@Query("select r.userMap.userId from Review r where r.movieMap.movieId=?1")
	public List<Integer> findUserId(int movieId);
	
	

}
