package vn.binh.service.Impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.binh.entity.Category_23110184;
import vn.binh.repositoty.CategoryRepository_23110184;
import vn.binh.service.ICategoryService_23110184;

import java.util.List;

@Service
public class CategoryServiceImpl_23110184 implements ICategoryService_23110184 {
    private final CategoryRepository_23110184 categoryRepository;

    public CategoryServiceImpl_23110184(CategoryRepository_23110184 categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category_23110184> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void insert(Category_23110184 category) {
        categoryRepository.save(category);
    }

    @Override
    public void edit(Category_23110184 category) {
        categoryRepository.save(category);
    }

    @Override
    public void delete(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Page<Category_23110184> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }
}
