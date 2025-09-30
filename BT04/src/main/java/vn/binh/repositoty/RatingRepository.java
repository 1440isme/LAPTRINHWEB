package vn.binh.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

import vn.binh.entity.Rating;
import vn.binh.entity.RatingId;

@Repository
public interface RatingRepository extends JpaRepository<Rating, RatingId> {
    List<Rating> findByBook_BookId(Integer bookId);

    int countByBook_BookId(Integer bookId);

    boolean existsByUser_IdAndBook_BookId(Integer userId, Integer bookId);

    @Query("SELECT r FROM Rating r WHERE r.user.id = :userId AND r.book.bookId = :bookId")
    Rating findByUserIdAndBookId(@Param("userId") Integer userId, @Param("bookId") Integer bookId);

}
