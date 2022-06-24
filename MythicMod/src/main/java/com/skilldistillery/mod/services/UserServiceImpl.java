package com.skilldistillery.mod.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.mod.entities.User;
import com.skilldistillery.mod.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public User getUserById(int userId) {
		Optional<User> userOpt = userRepo.findById(userId);
		User user = null;
		if(userOpt.isPresent()) {
			user = userOpt.get();
		}
		return user;
	}
	
//-----------------------------------New------------------------------------------------------

	@Override
	public User showUser(String username, int id) {
		
		return userRepo.findByIdAndUser_Username(id, username);
	}

	@Override
	public User createUser(User user) {
		
		return userRepo.saveAndFlush(user);
	
		} 
		


	@Override
	public User updateUser(String username, int id, User user) {
		User managed = userRepo.findByIdAndUser_Username(id, username);
		if(managed != null) {
			managed.setFirstName(user.getFirstName());
			managed.setLastName(user.getLastName());
			managed.setImage(user.getImage());
			managed.setEmail(user.getEmail());
			managed.setEnabled(user.isEnabled());
			managed.setPassword(user.getPassword());
			managed.setRole(user.getRole());
		}
	
		userRepo.saveAndFlush(managed);
		return managed;
	}

	@Override
	public boolean destroyUser(String username, int id) {
		userRepo.deleteById(id);
		boolean deleted = !userRepo.existsById(id);
		return deleted;
	}

}
