package com.skilldistillery.mod.services;

import java.util.List;

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
		User userOpt = userRepo.findById(userId);
		User user = null;
		if(userOpt != null) {
			return user;
			
		}
		else {
			return null;
		}
	}
	
//-----------------------------------New------------------------------------------------------

	@Override
	public User showUser(int id) {
		
		return userRepo.findById(id);
	}

	@Override
	public User createUser(User user) {
		
		return userRepo.saveAndFlush(user);
	
		} 
		


	@Override
	public User updateUser(int id, User user) {
		User managed = userRepo.findById(id);
		if(managed != null) {
			managed.setFirstName(user.getFirstName());
			managed.setLastName(user.getLastName());
			managed.setImage(user.getImage());
			managed.setEmail(user.getEmail());
			managed.setEnabled(user.isEnabled());
			
		}
	
		userRepo.saveAndFlush(managed);
		return managed;
	}

	@Override
	public boolean destroyUser(int id) {
		userRepo.deleteById(id);
		boolean deleted = !userRepo.existsById(id);
		return deleted;
	}
	
	@Override
	public List<User> userIndex() {

		return userRepo.findAll();
	}

}
