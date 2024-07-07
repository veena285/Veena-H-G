package com.example.imdb.responsedto;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.imdb.enums.Genre;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Component
public class MovieReponseDto {
	
	
	
	
	private String movieName;
	private Genre genre;
	private String language;
	private LocalTime movieDuration;
	private float movieRating;
	
    private Map<String, String> options;

	

	public Map<String, String> getOptions() {
	return options;
}
public void setOptions(Map<String, String> options) {
	this.options = options;
}
	@Override
	public String toString() {
		return "Movie [ movieName=" + movieName + ", genre=" + genre + ", language=" + language
				+ ", movieDuration=" + movieDuration + ", movieRating=" + movieRating 
				+ "]";
	}
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
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
	public float getMovieRating() {
		return movieRating;
	}
	public void setMovieRating(float movieRating) {
		this.movieRating = movieRating;
	}

}
