package com.decatholn.alert;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.decatholn.alert.exception.TeamStructureException;

@ControllerAdvice
public class ExceptionHandlerAdvice {
	
	
	@ExceptionHandler(TeamStructureException.class)
	public ResponseEntity<String> handleTeamException(Exception e){
		
		HttpStatus status = HttpStatus.EXPECTATION_FAILED;
		
		return new ResponseEntity<String>(e.getMessage(), status);
		
 	}

}
