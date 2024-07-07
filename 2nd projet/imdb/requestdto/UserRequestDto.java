package com.example.imdb.requestdto;

import java.time.LocalDate;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;



@Component
public class UserRequestDto {
	
	@NotBlank(message = "User cannot be blank")
	@jakarta.validation.constraints.NotNull(message = "User cannot be null")
	@Pattern(regexp = "[A-Z]{1}[a-zA-Z\\s]*", message = "Name should Start with capital letter")
   private  String userName;

	@NotBlank(message = "User cannot be blank")
	@jakarta.validation.constraints.NotNull(message = "User cannot be null")
	@Email(regexp = "[a-zA-Z0-9+_.-]+@[g][m][a][i][l]+.[c][o][m]", message = "invalid email--Should be in the extension of '@gmail.com' ")
   private  String userEmail;
	@NotBlank(message = "Customer cannot be blank")
	@jakarta.validation.constraints.NotNull(message = "User cannot be null")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "8 characters mandatory(1 upperCase,1   	lowerCase,1 special Character,1 number)")
   private  String userPassword; 
   private LocalDate dateOfBirth;
    
   
	@Override
	public String toString() {
		return "User [ userName=" + userName + ", userEmail=" + userEmail
				+ ", userPassword=" + userPassword + ", dateOfBirth=" + dateOfBirth + "]";
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
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
    
    
	
}
