package com.skilldistillery.mod.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mod.entities.Platform;

public interface PlatformRepository extends JpaRepository<Platform, Integer>{
	
	Platform findById(int id);

}
