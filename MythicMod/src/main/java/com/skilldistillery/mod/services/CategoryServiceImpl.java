package com.skilldistillery.mod.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.mod.entities.Category;
import com.skilldistillery.mod.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository catRepo;

	@Override
	public List<Category> index() {
		
		return catRepo.findAll();
	}

	@Override
	public Category show(int id) {
		
		return catRepo.findById(id);
	}

	@Override
	public Category create(Category category) {
		catRepo.saveAndFlush(category);
		
		
		
		
		return category;
	}

	@Override
	public Category update(int id, Category category) {
		// TODO Auto-generated method stub
		Category managed = catRepo.findById(id);
		if(managed != null) {
			managed.setGenre(category.getGenre());
			managed.setId(id);
			
			catRepo.saveAndFlush(managed);
			return managed;
		}
		return null;
	}

	@Override
	public boolean destroy(int id) {
		// TODO Auto-generated method stub
		if(catRepo.findById(id) != null) {
			catRepo.deleteById(id);
			return true;
		}
		return false;
	}

}
