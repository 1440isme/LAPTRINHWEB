package vn.binh.service;

import vn.binh.entity.Rating;

import java.util.List;

public interface IRatingService {
    List<Rating> getRating(Integer bookId);
    void insert(Rating rating);
    int countRatings(Integer bookId);
}
