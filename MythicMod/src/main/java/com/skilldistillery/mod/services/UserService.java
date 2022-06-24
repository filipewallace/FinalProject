package com.skilldistillery.mod.services;

import com.skilldistillery.mod.entities.User;

public interface UserService {

	User getUserById(int userId);
	
//	--------------------------new------------------------------------------
	
	 public User showUser(int id);

	 public User createUser(User user);

	 public User updateUser(int id, User user);

	 public boolean destroyUser(int id);

}
