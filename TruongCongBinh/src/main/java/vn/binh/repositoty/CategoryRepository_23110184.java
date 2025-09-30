package vn.binh.repositoty;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.binh.entity.Category_23110184;

@Repository
public interface CategoryRepository_23110184 extends JpaRepository<Category_23110184, Integer> {

    Page<Category_23110184> findAll(Pageable pageable);
}
