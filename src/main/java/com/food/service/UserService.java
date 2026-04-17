package com.food.service;

import com.food.models.User;

public interface UserService {
	
	public User findUserByJwtToken(String jwt)throws Exception;
	
	public User findUserByEmail(String email) throws Exception;
}
