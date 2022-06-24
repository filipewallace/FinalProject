package com.skilldistillery.mod.services;

import java.util.List;

import com.skilldistillery.mod.entities.Platform;

public interface PlatformService {
	
public List<Platform> index();
	
	public Platform show(int id);
	
	public Platform create(Platform platform);
	
	public Platform update(int id, Platform platform);
	
	public boolean destroy(int id);

}
