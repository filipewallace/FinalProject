package com.skilldistillery.mod.services;

import java.util.List;

import com.skilldistillery.mod.entities.EsrbRating;

public interface EsrbRatingService  {
	
	public List<EsrbRating> index();
	
	public EsrbRating show(int id);
	
	public EsrbRating create(EsrbRating esrbRating);
	
	public EsrbRating update(int id, EsrbRating esrbRating);
	
	public boolean destroy(int id);
	

}
