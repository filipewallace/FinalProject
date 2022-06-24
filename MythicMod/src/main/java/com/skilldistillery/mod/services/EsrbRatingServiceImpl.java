package com.skilldistillery.mod.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.mod.entities.Category;
import com.skilldistillery.mod.entities.EsrbRating;
import com.skilldistillery.mod.repositories.EsrbRespository;

import net.bytebuddy.utility.nullability.UnknownNull;

public class EsrbRatingServiceImpl implements EsrbRatingService {
	
	@Autowired
	private EsrbRespository esrbRepo;

	@Override
	public List<EsrbRating> index() {
		List<EsrbRating> result = esrbRepo.findAll();
		if(result != null) {
			return result;
		}
		return null;
	}

	@Override
	public EsrbRating show(int id) {
		
		EsrbRating result = esrbRepo.findById(id);
		if(result != null) {
			return result;
		}
		
		return null;
	}

	@Override
	public EsrbRating create(EsrbRating esrbRating) {
		if(esrbRating != null) {
			esrbRepo.saveAndFlush(esrbRating);
			return esrbRating;
		}
		return null;
	}

	@Override
	public EsrbRating update(int id, EsrbRating esrbRating) {
		EsrbRating managed = esrbRepo.findById(id);
		if(managed != null) {
			managed.setName(esrbRating.getName());
			esrbRepo.saveAndFlush(esrbRating);
			return managed;
		}
		return null;
	}

	@Override
	public boolean destroy(int id) {
		EsrbRating result = esrbRepo.findById(id);
		if(result != null) {
			esrbRepo.deleteById(id);
			return true;
		}
		
		return false;
	}



}
