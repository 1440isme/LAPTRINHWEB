package vn.binh.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.binh.entity.Category_23110184;

import java.util.List;

public interface ICategoryService_23110184 {
    List<Category_23110184> findAll();

    void insert(Category_23110184 category);

    void edit(Category_23110184 category);

    void delete(int id);

    Page<Category_23110184> findAll(Pageable pageable);

}
