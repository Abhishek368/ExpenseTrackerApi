package com.proj.expensetrackerapi.services;

import com.proj.expensetrackerapi.entity.User;
import com.proj.expensetrackerapi.entity.UserModel;

public interface UserService {
	User createUser(UserModel user);
	User readUser();
	User updateUser(UserModel user);
	void deleteUser();
	User getLoggedInUser();
}
