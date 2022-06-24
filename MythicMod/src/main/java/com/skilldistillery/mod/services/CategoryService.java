package com.skilldistillery.mod.services;

import java.util.List;

import com.skilldistillery.mod.entities.Category;

public interface CategoryService {
	
	public List<Category> index();
	
	public Category show(int id);
	
	public Category create(Category category);
	
	public Category update(int id, Category category);
	
	public boolean destroy(int id);
	
	

}
