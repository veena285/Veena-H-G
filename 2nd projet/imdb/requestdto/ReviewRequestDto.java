package com.example.imdb.requestdto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;


@Component
public class ReviewRequestDto {

	
	private String message;
	@NotNull
	@DecimalMin(value = "1", message="Movie rating should not be less than 1")
	@DecimalMax(value="5", message="Movie rating should not be more than 5")
	private float rating ;
	
	

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "Review [ message=" + message + ", rating=" + rating + "]";
	}
	
	

}
