package com.example.imdb.exceptionhandling;



import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.imdb.exceptionhandling.UserNotFoundException;
import com.example.imdb.utility.ErrorStructure;

@RestControllerAdvice
public class ApplicationHandler extends ResponseEntityExceptionHandler {
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		
	   List<ObjectError> list = ex.getAllErrors();
	   HashMap<String,String> hashMap = new HashMap<>();
	   for(ObjectError error:list)
	   {
		   FieldError fieldError = (FieldError)error;
		   String feildName=fieldError.getField();
		   String message = fieldError.getDefaultMessage();
		   hashMap.put(feildName, message);
		   
		   
	   }
		
		
		
		return new ResponseEntity<Object>(hashMap,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> UserNotFoundById(UserNotFoundException cnf)
	{     ErrorStructure<String> es = new ErrorStructure<String>();
	               es.setStatusCode(HttpStatus.NOT_FOUND.value());
	               es.setMessage(cnf.getMess()); // message whate we threw in service 
	               es.setErrordata(" USER WITH GIVEN ID NOT PRESENT ");
	               
	               return new ResponseEntity<ErrorStructure<String>>(es,HttpStatus.NOT_FOUND);



	}
	
	@ExceptionHandler(MovieNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> MovieNotFound(MovieNotFoundException cnf)
	{     ErrorStructure<String> es = new ErrorStructure<String>();
	               es.setStatusCode(HttpStatus.NOT_FOUND.value());
	               es.setMessage(cnf.getMess()); // message whate we threw in service 
	               es.setErrordata(" MOVIE WITH GIVEN ID NOT PRESENT ");
	               
	               return new ResponseEntity<ErrorStructure<String>>(es,HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler(MovieNotFoundByNameException.class)
	public ResponseEntity<ErrorStructure<String>> MovieNotFoundByName(MovieNotFoundByNameException cnf)
	{     ErrorStructure<String> es = new ErrorStructure<String>();
	               es.setStatusCode(HttpStatus.NOT_FOUND.value());
	               es.setMessage(cnf.getMess()); // message whate we threw in service 
	               es.setErrordata(" MOVIE WITH GIVEN NAME NOT PRESENT ");
	               
	               return new ResponseEntity<ErrorStructure<String>>(es,HttpStatus.NOT_FOUND);

	}
	
	
	
	@ExceptionHandler(ReviewNotFoundException.class)
	public ResponseEntity<ErrorStructure<String>> ReviewNotFound(ReviewNotFoundException cnf)
	{     ErrorStructure<String> es = new ErrorStructure<String>();
	               es.setStatusCode(HttpStatus.NOT_FOUND.value());
	               es.setMessage(cnf.getMess()); // message whate we threw in service 
	               es.setErrordata(" REVIEW  NOT PRESENT ");
	               
	               return new ResponseEntity<ErrorStructure<String>>(es,HttpStatus.NOT_FOUND);



	}
	
	

}
