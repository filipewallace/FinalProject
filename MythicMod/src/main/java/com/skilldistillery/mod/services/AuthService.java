package com.skilldistillery.mod.services;

import com.skilldistillery.mod.entities.User;

public interface AuthService {
	public User register(User user);
	public User getUserByUsername(String username);

}
