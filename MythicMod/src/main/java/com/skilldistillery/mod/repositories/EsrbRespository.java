package com.skilldistillery.mod.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mod.entities.EsrbRating;

public interface EsrbRespository extends JpaRepository<EsrbRating, Integer> {
	
	EsrbRating findById(int id);
	

}
