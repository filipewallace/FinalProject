package com.skilldistillery.mod.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.mod.entities.ModMedia;
import com.skilldistillery.mod.entities.User;
import com.skilldistillery.mod.repositories.ModMediaRepository;
import com.skilldistillery.mod.repositories.UserRepository;

public class ModMediaServiceImpl implements ModMediaService {
	
	@Autowired
	private ModMediaRepository modRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	
	@Override
	public List<ModMedia> index(String username) {
		if(userRepo.findByUsername(username) == null) {
			return null;
		}
		return modRepo.findByUser_Username(username);
	}
	

	@Override
	public ModMedia show(String username, int mId) {
		
		
		return modRepo.findByIdAndUser_Username(mId, username);
	}

	@Override
	public ModMedia create(String username, ModMedia modMedia) {
		User user = userRepo.findByUsername(username);
		if(user != null) {
			modMedia.setUser(user);
			return modRepo.saveAndFlush(modMedia);
		}
		
		return null;
	}

	@Override
	public ModMedia update(String username, int mId, ModMedia modMedia) {
		ModMedia managed = modRepo.findByIdAndUser_Username(mId, username);
		if(managed != null) {
			managed.setDescription(modMedia.getDescription());
			managed.setMediaUrl(modMedia.getMediaUrl());
			managed.setMod(modMedia.getMod());
			managed.setUser(modMedia.getUser());
			modRepo.saveAndFlush(managed);
			return managed;
		}
		
		return null;
	}

	@Override
	public boolean destroy(String username, int mId) {
		ModMedia managed = modRepo.findByIdAndUser_Username(mId, username);
		if(managed != null) {
			modRepo.deleteById(mId);
			return true;
		}
		
		return false;
	}

}
