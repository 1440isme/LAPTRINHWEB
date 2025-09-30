package vn.binh.service.Impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.binh.entity.Product_23110184;
import vn.binh.repositoty.ProductRepository_23110184;
import vn.binh.service.IProductService_23110184;

import java.util.List;

@Service
public class ProductServiceImpl_23110184 implements IProductService_23110184 {
    private final ProductRepository_23110184 productRepository;

    public ProductServiceImpl_23110184(ProductRepository_23110184 productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void insert(Product_23110184 product) {
        productRepository.save(product);
    }

    @Override
    public void edit(Product_23110184 product) {
        productRepository.save(product);
    }

    @Override
    public void delete(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product_23110184> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product_23110184> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product_23110184 findById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product_23110184> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return findAll();
        }
        return productRepository.findByProductNameContainingIgnoreCaseOrProductCodeContainingIgnoreCase(keyword,
                keyword);
    }
}
