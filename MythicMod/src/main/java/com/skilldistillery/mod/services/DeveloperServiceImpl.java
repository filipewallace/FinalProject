package com.skilldistillery.mod.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.mod.entities.Developer;
import com.skilldistillery.mod.repositories.DeveloperRepository;

@Service
public class DeveloperServiceImpl implements DeveloperService {

	@Autowired
	private DeveloperRepository devRepo;

	@Override
	public Developer getDeveloperById(int developerId) {

		Optional<Developer> developerOpt = devRepo.findById(developerId);
		Developer developer = null;

		if (developerOpt.isPresent()) {
			developer = developerOpt.get();

		}
		return developer;
	}

	@Override
	public Developer showDeveloper(String developerName, int developerId) {

		return devRepo.findByNameAndId(developerName, developerId);
	}

	@Override
	public List<Developer> developerIndex() {

		return devRepo.findAll();
	}

	@Override
	public Developer createDeveloper(Developer developer) {

		return devRepo.saveAndFlush(developer);
	}

	@Override
	public Developer updateDeveloper(int devId, Developer dev) {

		Developer updater = getDeveloperById(devId);

		if (updater != null) {

			updater.setName(dev.getName());
			updater.setImageUrl(dev.getImageUrl());
			updater.setWebLink(dev.getWebLink());
			updater.setGames(dev.getGames());

			dev = devRepo.saveAndFlush(updater);

			return dev;
		}

		return null;
	}

	@Override
	public boolean destroyDeveloper(int devId) {
		devRepo.deleteById(devId);
		boolean deleted = !devRepo.existsById(devId);
		return deleted;
	}

}
