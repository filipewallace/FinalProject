package com.skilldistillery.mod.services;

import java.util.List;
import java.util.Optional;

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
		Optional<Publisher> result = pubRepo.findById(id);

		Publisher p = null;
		if (result.isPresent()) {
			p = result.get();
		}

		return p;
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

		Optional<Publisher> managed = pubRepo.findById(id);
		Publisher p = managed.get();

		if (p != null) {
			p.setImageUrl(publisher.getImageUrl());
			p.setName(publisher.getName());
			p.setWebLink(publisher.getWebLink());

			pubRepo.saveAndFlush(p);

			return p;
		}

		return null;
	}

	@Override
	public boolean destroy(int id) {

		pubRepo.deleteById(id);

		boolean deleted = !pubRepo.existsById(id);

		return deleted;
	}

}
