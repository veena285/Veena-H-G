package com.example.imdb.exceptionhandling;

public class ReviewNotFoundException extends Exception {
	
	String mess;

	public ReviewNotFoundException(String mess) {
		super();
		this.mess = mess;
	}

	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}
	
	

}
