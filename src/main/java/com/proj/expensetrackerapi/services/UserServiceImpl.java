package com.proj.expensetrackerapi.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.proj.expensetrackerapi.entity.User;
import com.proj.expensetrackerapi.entity.UserModel;
import com.proj.expensetrackerapi.exceptions.ItemAlreadyExistsException;
import com.proj.expensetrackerapi.exceptions.ResourceNotFoundException;
import com.proj.expensetrackerapi.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public User createUser(UserModel user) {
		
		if(userRepo.existsByEmail(user.getEmail())) {
			throw new ItemAlreadyExistsException("User is already registered with this email" + user.getEmail());
		}
		User newUser = new User();
		BeanUtils.copyProperties(user, newUser);
		newUser.setPassword(bcryptEncoder.encode(newUser.getPassword()));
		return userRepo.save(newUser);
	}

	@Override
	public User readUser() {
		Long id = getLoggedInUser().getId();
		return userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found for the id "+ id));
	}

	@Override
	public User updateUser(UserModel user) {
		User u = readUser();
		u.setName(user.getName()!=null?user.getName():u.getName());
		u.setEmail(user.getEmail()!=null?user.getEmail():u.getEmail());
		u.setPassword(user.getPassword()!=null?bcryptEncoder.encode(user.getPassword()):u.getPassword());
		u.setAge(user.getAge()!=null?user.getAge():u.getAge());
		userRepo.save(u);
		return u;
	}

	@Override
	public void deleteUser(){
		User user = readUser();
		userRepo.delete(user);
	}

	@Override
	public User getLoggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		return userRepo.findByEmail(email).
				orElseThrow(()->new UsernameNotFoundException("User not found for email: "+email));
	}
}
