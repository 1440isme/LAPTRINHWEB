package vn.binh.service.impl;

import vn.binh.dao.RatingDAO;
import vn.binh.dao.impl.RatingDAOImpl;
import vn.binh.entity.Rating;
import vn.binh.service.IRatingService;

import java.util.List;

public class RatingServiceImpl implements IRatingService {
    private RatingDAO ratingDAO = new RatingDAOImpl();

    @Override
    public List<Rating> getRating(Integer bookId) {
        return ratingDAO.findByBookId(bookId);
    }

    @Override
    public void insert(Rating rating) {
        ratingDAO.insert(rating);
    }

    @Override
    public int countRatings(Integer bookId) {
        return ratingDAO.countByBookId(bookId);
    }
}
