package vn.binh.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.binh.entity.Product_23110184;

import java.util.List;

public interface IProductService_23110184 {
    void insert(Product_23110184 product);

    void edit(Product_23110184 product);

    void delete(int id);

    List<Product_23110184> findAll();

    Page<Product_23110184> findAll(Pageable pageable);

    Product_23110184 findById(int id);

    List<Product_23110184> search(String keyword);
}
