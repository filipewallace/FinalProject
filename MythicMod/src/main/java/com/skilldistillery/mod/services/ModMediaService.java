package com.skilldistillery.mod.services;

import java.util.List;

import com.skilldistillery.mod.entities.ModMedia;

public interface ModMediaService {

	public List<ModMedia> index(String username);
	
	public ModMedia show(String username, int mId);
	
	public ModMedia create(String username, ModMedia modMedia, int mId);
	
	public ModMedia update(String username, int mId, ModMedia modMedia);
	
	public boolean destroy(String username, int mId);
	
	
}
