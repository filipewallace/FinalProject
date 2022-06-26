package com.skilldistillery.mod.services;

import java.util.List;

import com.skilldistillery.mod.entities.Category;

public interface CategoryService {

	public Category getCategoryById(int categoryId);

	public Category showCategory(String categoryName, int categoryId);

	public List<Category> categoryIndex();

	public Category createCategory(Category category);

	public Category updateCategory(int categoryId, Category category);

	public boolean destroyCategory(int categoryId);

}
