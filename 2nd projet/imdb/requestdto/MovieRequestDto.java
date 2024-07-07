package com.example.imdb.requestdto;

import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.imdb.enums.Genre;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

@Component
public class MovieRequestDto {
	
	
	
	@Pattern(regexp = "^[a-zA-Z0-9\\s]*$", message = "Movie title should only contain letters, numbers, and spaces")
	private String movieName;
	
	@Pattern(regexp = "^(English|Hindi|Bengali|Telugu|Marathi|Tamil|Urdu|Gujarati|Kannada|Odia|Punjabi|Malayalam|Sanskrit|Assamese|Kashmiri|Nepali|Sindhi|Konkani|Manipuri|Maithili|Bodo|Dogri|Santali)$", message = "Invalid movie language")
	private String language;
  
	private LocalTime movieDuration;
	

	
	
	


	@Override
	public String toString() {
		return "Movie [ movieName=" + movieName + ", genre=" + ", language=" + language
				+ ", movieDuration=" + movieDuration  +"]";
	}
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public LocalTime getMovieDuration() {
		return movieDuration;
	}
	public void setMovieDuration(LocalTime movieDuration) {
		this.movieDuration = movieDuration;
	}




}
