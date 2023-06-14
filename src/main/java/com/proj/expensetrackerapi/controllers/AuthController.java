package com.proj.expensetrackerapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.proj.expensetrackerapi.entity.JwtResponse;
import com.proj.expensetrackerapi.entity.LoginModel;
import com.proj.expensetrackerapi.entity.User;
import com.proj.expensetrackerapi.entity.UserModel;
import com.proj.expensetrackerapi.security.CustomUserDetailsService;
import com.proj.expensetrackerapi.services.UserService;
import com.proj.expensetrackerapi.util.JwtTokenUtil;

import jakarta.validation.Valid;

@RestController
public class AuthController {
	
	@Autowired
	private  AuthenticationManager authManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody LoginModel user) throws Exception{
		authenticate(user.getEmail(),user.getPassword());
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
		
		final String token = jwtTokenUtil.generateToken(userDetails);
		
		return new ResponseEntity<JwtResponse>(new JwtResponse(token),HttpStatus.OK);
	}
	
	private void authenticate(String email, String password) throws Exception {
		// TODO Auto-generated method stub
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(email,password));
		}
		catch(DisabledException e) {
			throw new Exception("User Disabled");
		}
		catch (BadCredentialsException e) {
			// TODO: handle exception
			throw new Exception("Bad Credentials");
		}
		
	}

	public ResponseEntity<User> register(@Valid @RequestBody UserModel user){
		return new ResponseEntity<User>(userService.createUser(user),HttpStatus.CREATED);
	}
}
