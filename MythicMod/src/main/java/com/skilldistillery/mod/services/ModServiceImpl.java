package com.skilldistillery.mod.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.mod.entities.Mod;
import com.skilldistillery.mod.repositories.ModRepository;

@Service
public class ModServiceImpl implements ModService {

	@Autowired
	private ModRepository modRepo;

	@Override
	public Mod getModById(int modId) {
		Optional<Mod> modOpt = modRepo.findById(modId);
		Mod mod = null;
		if (modOpt.isPresent()) {
			mod = modOpt.get();
		}
		return mod;
	}

	@Override
	public Mod showMod(String modName, int modId) {

		return modRepo.findByTitleAndId(modName, modId);

	}

	@Override
	public List<Mod> modIndex() {

		return modRepo.findAll();
	}

	@Override
	public Mod createMod(Mod mod) {

		return modRepo.saveAndFlush(mod);

	}

	@Override
	public Mod updateMod(int modId, Mod mod) {

		Mod updater = getModById(modId);

		if (updater != null) {

			updater.setTitle(mod.getTitle());
			updater.setDescription(mod.getDescription());
			updater.setDateCreated(mod.getDateCreated());
			updater.setDateUpdated(mod.getDateUpdated());
			updater.setVersion(mod.getVersion());
			updater.setRequirements(mod.getRequirements());
			updater.setImageUrl(mod.getImageUrl());
			updater.setPrice(mod.getPrice());
			updater.setDownloadLink(mod.getDownloadLink());
			updater.setGame(mod.getGame());
			updater.setUser(mod.getUser());
			updater.setModMedias(mod.getModMedias());
			updater.setPosts(mod.getPosts());

			mod = modRepo.saveAndFlush(updater);

			return mod;
		}

		return null;
	}

	@Override
	public boolean destroyMod(int modId) {

		modRepo.deleteById(modId);
		boolean deleted = !modRepo.existsById(modId);
		return deleted;
	}

	// FIND ALL MODS WITH USER ID
	@Override
	public List<Mod> getModsByUser(String username) {

		
			
		return modRepo.findByUser_Username(username);
	}

	@Override
	public List<Mod> getModsByGame(Integer gameId) {

		if (!modRepo.existsById(gameId)) {
			return null;
		}
		return modRepo.findByGame_Id(gameId);
	}

}
