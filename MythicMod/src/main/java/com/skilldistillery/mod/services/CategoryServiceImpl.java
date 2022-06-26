package com.skilldistillery.mod.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.mod.entities.Category;
import com.skilldistillery.mod.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository catRepo;

	@Override
	public Category getCategoryById(int categoryId) {

		Optional<Category> categoryOpt = catRepo.findById(categoryId);
		Category category = null;
		if (categoryOpt.isPresent()) {
			category = categoryOpt.get();
		}
		return category;
	}

	@Override
	public Category showCategory(String categoryGenre, int categoryId) {

		return catRepo.findByGenreAndId(categoryGenre, categoryId);

	}

	@Override
	public List<Category> categoryIndex() {
		return catRepo.findAll();
	}

	@Override
	public Category createCategory(Category category) {

		return catRepo.saveAndFlush(category);
	}

	@Override
	public Category updateCategory(int categoryId, Category category) {

		Category updater = getCategoryById(categoryId);

		if (updater != null) {

			updater.setGenre(category.getGenre());

			category = catRepo.saveAndFlush(updater);
			return category;

		}

		return null;
	}

	@Override
	public boolean destroyCategory(int categoryId) {

		catRepo.deleteById(categoryId);
		boolean deleted = !catRepo.existsById(categoryId);

		return deleted;
	}
}
