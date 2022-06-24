package com.skilldistillery.mod.services;

import java.util.List;

import com.skilldistillery.mod.entities.Publisher;

public interface PublisherService {
	
	public List<Publisher> index();
	
	public Publisher show(int id);
	
	public Publisher create(Publisher publisher);
	
	public Publisher update(int id, Publisher publisher);
	
	public boolean destroy(int id);

}
