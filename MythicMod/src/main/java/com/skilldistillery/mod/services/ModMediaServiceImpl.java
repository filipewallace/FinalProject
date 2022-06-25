package com.skilldistillery.mod.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.mod.entities.ModMedia;
import com.skilldistillery.mod.repositories.ModMediaRepository;

@Service
public class ModMediaServiceImpl implements ModMediaService {
	
	@Autowired
	private ModMediaRepository modRepo;
	
	
	
	
	@Override
	public List<ModMedia> index() {
		
		
		return modRepo.findAll();
	}
	

	@Override
	public ModMedia show(int mId) {
	Optional<ModMedia> mod = modRepo.findById(mId);
	ModMedia modmedia = null;
		if(mod.isPresent()) {
			modmedia = mod.get();
		}
		
		
		return modmedia;
	}

	@Override
	public ModMedia create(ModMedia modMedia) {
			
			return modRepo.saveAndFlush(modMedia);
		
	}

	@Override
	public ModMedia update(int mId, ModMedia modMedia) {
		Optional<ModMedia> managed = modRepo.findById(mId);
		ModMedia m = managed.get();
		
		if(m != null) {
			m.setDescription(modMedia.getDescription());
			m.setMediaUrl(modMedia.getMediaUrl());
			m.setMod(modMedia.getMod());
			m.setUser(modMedia.getUser());
			m.setMod(modMedia.getMod());
			modRepo.saveAndFlush(m);
			return m;
		}
		
		return null;
	}

	@Override
	public boolean destroy(int mId) {
		
		modRepo.deleteById(mId);
		
		boolean	deleted = !modRepo.existsById(mId);
		
		return deleted;
	}
	
	

}
