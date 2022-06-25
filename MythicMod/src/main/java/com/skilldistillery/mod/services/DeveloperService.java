package com.skilldistillery.mod.services;

import java.util.List;

import com.skilldistillery.mod.entities.Developer;

public interface DeveloperService {
	
Developer getDeveloperById(int developerId);
	
//	--------------------------new------------------------------------------
	
public Developer showDeveloper(String developerName, int developerId);

public List<Developer> developerIndex();

public Developer createDeveloper( Developer developer);

public Developer updateDeveloper(int developerId, Developer developer);

public boolean destroyDeveloper(int developerId);


}
 