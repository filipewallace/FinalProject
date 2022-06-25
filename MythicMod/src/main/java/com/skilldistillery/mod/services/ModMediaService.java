package com.skilldistillery.mod.services;

import java.util.List;
import java.util.Optional;

import com.skilldistillery.mod.entities.ModMedia;

public interface ModMediaService {

	public List<ModMedia> index();
	
	public ModMedia show(int mId);
	
	public ModMedia create(ModMedia modMedia);
	
	public ModMedia update(int mId, ModMedia modMedia);
	
	public boolean destroy(int mId);
	
	
}
