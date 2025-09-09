package vn.binh.service;

import java.util.List;

import vn.binh.entity.Category;

public interface CategoryService {
	void insert(Category category);

	void edit(Category category);

	void delete(int id);

	Category getIdCategory(int id);

	Category getNameCategory(String name);

	List<Category> getAll();

	List<Category> search(String keyword);
}