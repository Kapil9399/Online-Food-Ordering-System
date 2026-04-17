package com.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.food.models.User;

public interface UserRepository  extends JpaRepository<User,Long>{

	public User findByEmail(String username);
}
