package com.skilldistillery.mod.services;

import java.util.List;

import com.skilldistillery.mod.entities.Mod;

public interface ModService {
	
	public Mod getModById(int modId);
	
	public Mod showMod(String modName, int modId);

	public List<Mod> modIndex();

	public Mod createMod( Mod mod);

	public Mod updateMod(int modId, Mod mod);

	public boolean destroyMod(int modId);
	
	public List<Mod> getModsByUser(String username);
	
	public List<Mod> getModsByGame(Integer gameId);


}
