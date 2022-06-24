package com.skilldistillery.mod.services;

import com.skilldistillery.mod.entities.User;

public interface UserService {

	User getUserById(int userId);
	
//	--------------------------new------------------------------------------
	
	 public User showUser(String username, int tid);

	 public User createUser(User user);

	 public User updateUser(String username, int tid, User user);

	 public boolean destroyUser(String username, int tid);

}
