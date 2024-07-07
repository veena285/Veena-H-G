package com.example.imdb.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.imdb.Entity.Movie;
import com.example.imdb.enums.Genre;
import com.example.imdb.exceptionhandling.MovieNotFoundByGenreException;
import com.example.imdb.exceptionhandling.MovieNotFoundByNameException;
import com.example.imdb.exceptionhandling.MovieNotFoundException;
import com.example.imdb.repository.MovieRepository;
import com.example.imdb.requestdto.MovieRequestDto;
import com.example.imdb.responsedto.MovieReponseDto;
import com.example.imdb.utility.ResponseStructure;

@Service
public class MovieService {
	
	@Autowired
	MovieRepository repo;	
	
	private Movie convertToMovie(MovieRequestDto movReq ,Movie movie )
	{
	
		
		
		movie.setLanguage(movReq.getLanguage());
		movie.setMovieDuration(movReq.getMovieDuration());
		movie.setMovieName(movReq.getMovieName());
	
        return movie;
	}
	
	
	private MovieReponseDto convertToMovieRespnse (Movie movie)
	{
		MovieReponseDto ur= new MovieReponseDto();
		ur.setMovieName(movie.getMovieName());
		ur.setMovieRating(movie.getMovieRating());
		ur.setMovieDuration(movie.getMovieDuration());
		ur.setLanguage(movie.getLanguage());
		ur.setGenre(movie.getGenre());
	
		return ur;
		
		
	}

	public ResponseEntity<ResponseStructure<String>> insertMovie(MovieRequestDto movReq, Genre genre) {
		
	
				
		Movie movie = convertToMovie(movReq, new Movie());
		movie.setGenre(genre);
		repo.save(movie);
		
		
		ResponseStructure<String> rs = new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setMessage(" Movie data saved successfully");
		rs.setData(" 1 MOVIE ADDED  SUCCESSFULLY");
		
	
		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.CREATED);

		
	
	}

	public ResponseEntity<ResponseStructure<MovieReponseDto>> findById(int id) throws MovieNotFoundException {
		
		ResponseStructure<MovieReponseDto> rs = new ResponseStructure<MovieReponseDto>();
		Optional<Movie> id2 = repo.findById(id);
		
		if (id2.isPresent()) {
			Movie ca = id2.get();
			MovieReponseDto dto = convertToMovieRespnse(ca);
			
			
			Map<String,String> o = new HashMap<>();
		
			o.put("Reviews","/movies/"+ca.getMovieId()+"/reviews");
		
			dto.setOptions(o);
			
			rs.setStatusCode(HttpStatus.FOUND.value());
			rs.setMessage(" Movie Fetched successfully");
			rs.setData(dto);
			return new ResponseEntity<ResponseStructure<MovieReponseDto>>(rs, HttpStatus.FOUND);

		} 
	
		else throw new MovieNotFoundException(" Movie not found");
		
		
		
	}
	
	public ResponseEntity<ResponseStructure<String>> updateMovie(MovieRequestDto movieup,int movieid) throws MovieNotFoundException {
		
		
        Optional<Movie> optMovieExis = repo.findById(movieid);
        
        if(optMovieExis.isPresent()) { // check first whether the id is prsent or no
        	
        	Movie existing = optMovieExis.get(); //if present get the object
        	
        	 Movie updated = convertToMovie(movieup,existing); // convert the  paramter objectrequest to MOvie type to update
        	 
                //  updated.setMovieId(existing.getMovieId()); // set the Id from previous existing Object 
                  
            repo.save(updated); // update the new object in database
            
       ResponseStructure<String> rs = new ResponseStructure<>();
     rs.setStatusCode(HttpStatus.FOUND.value());
         rs.setMessage(" Movie data updated successfully");
       rs.setData(" MOVIE UPDATED SUCCESSFULLY");


             return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.FOUND);
        }
        
        else throw new MovieNotFoundException("movie not found with this Id");

	}

	public ResponseEntity<ResponseStructure<String>> deleteById(int id) throws MovieNotFoundException {
	      Optional<Movie> id2 = repo.findById(id);
	        
	        if(id2.isPresent()) {
	            repo.deleteById(id);
	ResponseStructure<String> rs = new ResponseStructure<>();
	rs.setStatusCode(HttpStatus.FOUND.value());
	rs.setMessage(" Movie data deleted successfully");
	rs.setData(" MOVIE DELETED SUCCESSFULLY");


	return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.FOUND);
	        }
	        
	        else throw new MovieNotFoundException("Movie not found with this Id");

	}

	public ResponseEntity<ResponseStructure<List<MovieReponseDto>>> findAll() throws MovieNotFoundException {
		
	      List<Movie> list = repo.findAll();
	   
	        
	        if(!list.isEmpty()) {
	        	
	        	   List<MovieReponseDto> resMovlist = new ArrayList<>() ;
	        	   
	     	      for(Movie i:list) {
	     	    	  MovieReponseDto dto = convertToMovieRespnse(i);
	     	    	 Map<String,String> o = new HashMap<>();
	     			o.put("reviews","/reviews");
	     			dto.setOptions(o);
	     			
	     	    	  resMovlist.add(dto);
	     	      }
	        	   
//	        	   Iterator<Movie> it = list.iterator();
//	        	   
//	        	   while(it.hasNext()) {
//	        		   MovieReponseDto dto = convertToMovieRespnse(it.next()); 
//	        		   resMovlist.add(dto);
//	        	   }
	         
	ResponseStructure<List<MovieReponseDto>> rs = new ResponseStructure<>();
	rs.setStatusCode(HttpStatus.FOUND.value());
	rs.setMessage(" Movie data fetchedsuccessfully");
	rs.setData(resMovlist);


	return new ResponseEntity<ResponseStructure<List<MovieReponseDto>>>(rs, HttpStatus.FOUND);
	        }
	        
	        else throw new MovieNotFoundException("no movies found");

	}


	public ResponseEntity<ResponseStructure<List<MovieReponseDto>>> findByName(String name) throws MovieNotFoundByNameException {
		
		List<Movie> list = repo.findByName(name);
		
		    if(!list.isEmpty()) {
		    	
		    	List<MovieReponseDto> resList = new ArrayList<>();
		    	for(Movie i: list)
		    	{  MovieReponseDto dto = convertToMovieRespnse(i);
		    		Map<String,String> o = new HashMap<>();
					o.put("reviews","/reviews");
					dto.setOptions(o);
					
		    		
		    		resList.add(dto);
		    		
		    	}
		    	
		    	
		    	
		    	ResponseStructure<List<MovieReponseDto>> rs= new ResponseStructure<List<MovieReponseDto>>();	
		    	
		    	rs.setStatusCode(HttpStatus.FOUND.value());
		    	rs.setMessage("Movies data found Successfully");
		    	rs.setData(resList);
		    	
		    	return new ResponseEntity<ResponseStructure<List<MovieReponseDto>>>(rs,HttpStatus.FOUND);
		    	
		    }
		    
		    else throw new MovieNotFoundByNameException("Movie with given name not Found");
		
	}


	public ResponseEntity<ResponseStructure<List<MovieReponseDto>>> findByGenre(Genre genreName) throws MovieNotFoundByGenreException  {
		
		  
		List<Movie> list = repo.findByGenre(genreName);
		
	    if(!list.isEmpty()) {
	    	List<MovieReponseDto> resList = new ArrayList<>();
	    	for(Movie i: list)
	    	{
	    		resList.add(convertToMovieRespnse(i));
	    		
	    	}
	    	
	    	
	    	
	    	ResponseStructure<List<MovieReponseDto>> rs= new ResponseStructure<List<MovieReponseDto>>();	
	    	
	    	rs.setStatusCode(HttpStatus.FOUND.value());
	    	rs.setMessage("Movies data found Successfully");
	    	rs.setData(resList);
	    	
	    	return new ResponseEntity<ResponseStructure<List<MovieReponseDto>>>(rs,HttpStatus.FOUND);
	    	
	    }
	    
	    else throw new MovieNotFoundByGenreException("Movie with given name not Found");
	
	}
	

}
