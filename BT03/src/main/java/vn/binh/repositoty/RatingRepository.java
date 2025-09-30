package vn.binh.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import vn.binh.entity.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
    List<Rating> findByBook_BookId(Integer bookId);

    int countByBook_BookId(Integer bookId);

}
