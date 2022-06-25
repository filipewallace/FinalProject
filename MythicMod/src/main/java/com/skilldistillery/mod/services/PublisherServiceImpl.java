package com.skilldistillery.mod.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.mod.entities.Publisher;
import com.skilldistillery.mod.repositories.PublisherRepository;

@Service
public class PublisherServiceImpl implements PublisherService {
	
	@Autowired
	private PublisherRepository pubRepo;

	@Override
	public List<Publisher> index() {
		List<Publisher> result = pubRepo.findAll();
		
		if (result == null) {
			return null;
		}
		
		return result;
	}

	@Override
	public Publisher show(int id) {
		Publisher result = pubRepo.findById(id);
		
		if (result == null) {
			return null;
		}
		
		return result;
	}

	@Override
	public Publisher create(Publisher publisher) {
		
		if (publisher != null) {
			pubRepo.saveAndFlush(publisher);
			return publisher;
		}
		
		return null;
	}

	@Override
	public Publisher update(int id, Publisher publisher) {
		
		Publisher managed = pubRepo.findById(id);
		
		if (managed != null) {
			managed.setImageUrl(publisher.getImageUrl());
			managed.setName(publisher.getName());
			managed.setWebLink(publisher.getWebLink());
			
			pubRepo.saveAndFlush(managed);
			
			return managed;
		}
		
		return null;
	}

	@Override
	public boolean destroy(int id) {
		
		Publisher result = pubRepo.findById(id);
		
		if (result != null) {
			pubRepo.deleteById(id);
			return true;
		}
		
		return false;
	}

}
