package vn.binh.service.Impl;

import vn.binh.repositoty.RatingRepository;
import vn.binh.entity.Rating;
import vn.binh.service.IRatingService;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements IRatingService {
    private final RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public List<Rating> getRating(Integer bookId) {
        return ratingRepository.findByBook_BookId(bookId);
    }

    @Override
    public void insert(Rating rating) {
        ratingRepository.save(rating);
    }

    @Override
    public int countRatings(Integer bookId) {
        return ratingRepository.countByBook_BookId(bookId);
    }
}
