package com.skilldistillery.mod.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mod.entities.Mod;

public interface ModRepository extends JpaRepository<Mod, Integer> {
	
	Mod findByTitle(String modTitle);
	
	Mod findByTitleAndId(String modTitle, int modId);
	
	Mod queryById(int id);
	
	List <Mod> findByUser_Id(Integer userId);
	
	List <Mod> findByGames_Id(Integer gameId);
	

}
