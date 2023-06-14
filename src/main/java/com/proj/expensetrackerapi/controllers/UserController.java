package com.proj.expensetrackerapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.proj.expensetrackerapi.entity.User;
import com.proj.expensetrackerapi.entity.UserModel;
import com.proj.expensetrackerapi.services.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/profile")
	public User readUser() {
		return service.readUser();
	}
	
	@PostMapping("/register")
	public ResponseEntity<User> save(@Valid @RequestBody UserModel user){
		return new ResponseEntity<User>(service.createUser(user),HttpStatus.CREATED);
	} 
	
	@PutMapping("/profile")
	public ResponseEntity<User> updateUser( @RequestBody UserModel user){
		return new ResponseEntity<User>(service.updateUser(user),HttpStatus.OK);
	}
	
	@DeleteMapping("/deactivate")
	public ResponseEntity<User> deleteUser(){
		service.deleteUser();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
}
