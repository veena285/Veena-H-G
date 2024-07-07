package com.example.imdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.imdb.Entity.Movie;
import com.example.imdb.Entity.User;
import com.example.imdb.enums.Genre;
import com.example.imdb.exceptionhandling.MovieNotFoundByGenreException;
import com.example.imdb.exceptionhandling.MovieNotFoundByNameException;
import com.example.imdb.exceptionhandling.MovieNotFoundException;
import com.example.imdb.exceptionhandling.UserNotFoundException;
import com.example.imdb.requestdto.MovieRequestDto;
import com.example.imdb.responsedto.MovieReponseDto;
import com.example.imdb.service.MovieService;
import com.example.imdb.utility.ResponseStructure;

import jakarta.validation.Valid;

@RestController
public class MovieController {
	
	@Autowired
	MovieService ms;
	
	
	@PostMapping("/genres/{genre}/movies")   
	public ResponseEntity<ResponseStructure<String>> inserMovie(@RequestBody @Valid MovieRequestDto li, @PathVariable Genre genre)
	{
		
		 return ms.insertMovie(li, genre);
		
	}
	
	@GetMapping("/movies/{id}")   
	public ResponseEntity<ResponseStructure<MovieReponseDto>> findById(@PathVariable int id) throws MovieNotFoundException
	{
		
		 return ms.findById(id);
		
	}
	
	@GetMapping("names/{name}/movies")   
	public ResponseEntity<ResponseStructure<List<MovieReponseDto>>> findByName(@PathVariable String name) throws  MovieNotFoundByNameException
	{
		
		 return ms.findByName(name);
		
	}
	
//	@GetMapping("genre/{genrename}/movies")   
//	public ResponseEntity<ResponseStructure<List<MovieReponseDto>>> findByGenre(@PathVariable String genrename) throws MovieNotFoundByGenreException
//	{
//		
//		 return ms.findByGenre(genrename);
//		
//	}
	
	
	@GetMapping("/movies")   // working
	public ResponseEntity<ResponseStructure<List<MovieReponseDto>>> findAll() throws MovieNotFoundException
	{
		
		 return ms.findAll();
		
	}
	
	@PutMapping("/movies/{id}")   // working
	public ResponseEntity<ResponseStructure<String>> updateMovie(@RequestBody @Valid MovieRequestDto li,@PathVariable int id) throws MovieNotFoundException
	{
		
		 return ms.updateMovie(li,id);
		
	}
	
	@DeleteMapping("/movies/{id}")   // working
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable int id) throws MovieNotFoundException
	{
		
		 return ms.deleteById(id);
		
	}
	
	
	}
