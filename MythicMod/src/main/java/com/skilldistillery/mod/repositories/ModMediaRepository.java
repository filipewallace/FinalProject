package com.skilldistillery.mod.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mod.entities.ModMedia;

public interface ModMediaRepository extends JpaRepository<ModMedia, Integer> {
	
	List<ModMedia> findByUser_Username(String username);
	
	ModMedia findByIdAndUser_Username(int id, String username);
	
	

}
