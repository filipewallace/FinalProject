package com.skilldistillery.mod.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.mod.entities.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

}
