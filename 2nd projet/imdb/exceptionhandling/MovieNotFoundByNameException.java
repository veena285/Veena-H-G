package com.example.imdb.exceptionhandling;

public class MovieNotFoundByNameException extends Exception {
	
	String mess;

	public MovieNotFoundByNameException(String mess) {
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
