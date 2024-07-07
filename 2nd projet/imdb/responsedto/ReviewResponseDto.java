package com.example.imdb.responsedto;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.imdb.Entity.User;


@Component
public class ReviewResponseDto {
	
	private int reviewId;
	private String message;
	private float rating ;
	
  public Map<String, String> getOptions() {
		return options;
	}
	public void setOptions(Map<String, String> options) {
		this.options = options;
	}
private Map<String,String> options;
	
	
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
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
		return "Review [reviewId=" + reviewId + ", message=" + message + ", rating=" + rating + "]";
	}
	
	

}
