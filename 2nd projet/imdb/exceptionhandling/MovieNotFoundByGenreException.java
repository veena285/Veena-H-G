package com.example.imdb.exceptionhandling;

public class MovieNotFoundByGenreException extends Exception {
	String mess;

	public MovieNotFoundByGenreException(String mess) {
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
