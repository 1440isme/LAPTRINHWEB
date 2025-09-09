package vn.binh.service.impl;

import java.util.List;

import vn.binh.dao.CategoryDAO;
import vn.binh.dao.impl.CategoryDAOImpl;
import vn.binh.entity.Category;
import vn.binh.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	CategoryDAO cateDAO = new CategoryDAOImpl();

	@Override
	public void insert(Category category) {
		cateDAO.create(category);
	}

	@Override
	public void edit(Category category) {
		cateDAO.update(category);
	}

	@Override
	public void delete(int id) {
		cateDAO.remove(id);
	}

	@Override
	public List<Category> getAll() {
		return cateDAO.findAll();
	}

	@Override
	public List<Category> search(String keyword) {
		return cateDAO.search(keyword);
	}

	@Override
	public Category getIdCategory(int id) {
		return cateDAO.findById(id);
	}

	@Override
	public Category getNameCategory(String name) {
		return cateDAO.findByName(name);

	}

}