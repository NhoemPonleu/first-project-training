package com.acledabankplc.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class ApiException extends RuntimeException{ 
	
	private HttpStatus status;
	private String message;
}
