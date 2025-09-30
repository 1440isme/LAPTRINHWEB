package vn.binh.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.binh.entity.Product_23110184;
import java.util.List;

@Repository
public interface ProductRepository_23110184 extends JpaRepository<Product_23110184, Integer> {
    List<Product_23110184> findByProductNameContainingIgnoreCaseOrProductCodeContainingIgnoreCase(String productName,
            String productCode);
}
