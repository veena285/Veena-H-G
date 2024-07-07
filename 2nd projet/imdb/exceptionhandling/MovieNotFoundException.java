package com.example.imdb.exceptionhandling;

public class MovieNotFoundException extends Exception {
	
	String mess;

	public MovieNotFoundException(String mess) {
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
