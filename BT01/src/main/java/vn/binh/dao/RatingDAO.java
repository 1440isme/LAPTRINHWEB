package vn.binh.dao;

import vn.binh.entity.Rating;

import java.util.List;

public interface RatingDAO {
    List<Rating> findByBookId(Integer bookId);
    void insert(Rating rating);
    int countByBookId(Integer bookId);
}
