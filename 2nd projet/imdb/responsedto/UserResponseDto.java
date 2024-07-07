package com.example.imdb.responsedto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Component
public class UserResponseDto {
	
	
	
   private  int userId;
   private  String userName;
   private  String userEmail;
  
   private LocalDate dateOfBirth;
    
 
	@Override
	public String toString() {
		return "User [ userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail
				+ ", dateOfBirth=" + dateOfBirth + "]";
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int i) {
		this.userId = i;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
    
    
	
}
