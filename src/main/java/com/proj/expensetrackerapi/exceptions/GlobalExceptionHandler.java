package com.proj.expensetrackerapi.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.proj.expensetrackerapi.entity.ErrorObject;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorObject> handleExpenseNotFoundException(ResourceNotFoundException ex, WebRequest request){
		ErrorObject er = new ErrorObject();
		
		er.setStatusCode(HttpStatus.NOT_FOUND.value());
		er.setMessage(ex.getMessage());
		er.setTimestamp(new Date());
		return new ResponseEntity<>(er,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorObject> handleBadRequestException(MethodArgumentTypeMismatchException ex){
		ErrorObject er = new ErrorObject();
		er.setStatusCode(HttpStatus.BAD_REQUEST.value());
		er.setMessage(ex.getMessage());
		er.setTimestamp(new Date());
		return new ResponseEntity<ErrorObject>(er,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorObject> handleGeneralException(Exception ex){
		ErrorObject er = new ErrorObject();
		er.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		er.setMessage(ex.getMessage());
		er.setTimestamp(new Date());
		return new ResponseEntity<ErrorObject>(er,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		
		Map<String,Object> body = new HashMap<>();
		body.put("timestamp", new Date());
		body.put("status code", HttpStatus.BAD_REQUEST.value());
		
		 List<String> errors = ex.getBindingResult()
			.getFieldErrors()
			.stream()
			.map(x-> x.getDefaultMessage())
			.collect(Collectors.toList());
		
		body.put("message" , errors);
		return new ResponseEntity<Object>(body,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ItemAlreadyExistsException.class)
	public ResponseEntity<ErrorObject> handleItemAlreadyExistsException(ItemAlreadyExistsException ex){
		ErrorObject er = new ErrorObject();
		er.setStatusCode(HttpStatus.CONFLICT.value());
		er.setMessage(ex.getMessage());
		er.setTimestamp(new Date());
		return new ResponseEntity<ErrorObject>(er,HttpStatus.CONFLICT);
	}
	
}
