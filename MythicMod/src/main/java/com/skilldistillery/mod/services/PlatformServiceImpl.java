package com.skilldistillery.mod.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.mod.entities.Platform;
import com.skilldistillery.mod.repositories.PlatformRepository;

@Service
public class PlatformServiceImpl implements PlatformService {
	
	@Autowired
	PlatformRepository platRepo;

	@Override
	public List<Platform> index() {
		
		List<Platform> result = platRepo.findAll();
		
		if (result == null) {
			return null;
		}
		
		return result;
	}

	@Override
	public Platform show(int id) {
		Optional<Platform> platOpt = platRepo.findById(id);
		
		Platform platfrom = null;
		if (platOpt.isPresent()) {
			platfrom = platOpt.get();
		}
		return platfrom;
		
		
	}

	@Override
	public Platform create(Platform platform) {
		
		if (platform != null) {
			platRepo.saveAndFlush(platform);
			return platform;
		}
		return null;
	}

	@Override
	public Platform update(int id, Platform platform) {
		
		Platform managed = show(id);
		if (managed != null) {
			managed.setName(platform.getName());
			
			platRepo.saveAndFlush(managed);
			
			return managed;
		}
		
		return null;
	}

	@Override
	public boolean destroy(int id) {
		Platform result =  show(id);
		
		if (result != null) {
			platRepo.deleteById(id);
			return true;
		}
		return false;
	}

}
