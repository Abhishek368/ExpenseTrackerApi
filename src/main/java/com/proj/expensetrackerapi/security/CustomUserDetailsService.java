package com.proj.expensetrackerapi.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.proj.expensetrackerapi.entity.User;
import com.proj.expensetrackerapi.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 User existinguser = userRepo
				 			.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("Username not found by the email:"+username));
		 return new org.springframework.security.core.userdetails.User(existinguser.getEmail(), existinguser.getPassword(), new ArrayList<>()); 
	}

}
