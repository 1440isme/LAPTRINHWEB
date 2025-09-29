package vn.binh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.binh.config.DBConnection;
import vn.binh.dao.RatingDAO;
import vn.binh.entity.Book;
import vn.binh.entity.Rating;
import vn.binh.entity.User;

public class RatingDAOImpl extends DBConnection implements RatingDAO {
    @Override
    public List<Rating> findByBookId(Integer bookId) {
        String sql = "SELECT id, rating, reviewText, userid, bookid FROM rating WHERE bookid = ?";
        List<Rating> ratings = new ArrayList<>();
        try {
            Connection con = super.getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, bookId);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        ratings.add(mapRow(rs));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ratings;
    }

    @Override
    public void insert(Rating rating) {
        String nextIdSql = "SELECT ISNULL(MAX(id), 0) + 1 FROM rating";
        String insertSql = "INSERT INTO rating(id, rating, reviewText, userid, bookid) VALUES(?, ?, ?, ?, ?)";
        try {
            Connection con = super.getConnection();
            int newId = 1;
            try (PreparedStatement ps = con.prepareStatement(nextIdSql);
                    ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    newId = rs.getInt(1);
                }
            }
            try (PreparedStatement ps = con.prepareStatement(insertSql)) {
                ps.setInt(1, newId);
                ps.setInt(2, rating.getRating());
                ps.setString(3, rating.getReviewText());
                ps.setInt(4, rating.getUser().getId());
                ps.setInt(5, rating.getBook().getBookId());
                ps.executeUpdate();
            }
            rating.setId(newId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to insert rating: " + e.getMessage(), e);
        }
    }

    @Override
    public int countByBookId(Integer bookId) {
        String sql = "SELECT COUNT(*) FROM rating WHERE bookid = ?";
        try {
            Connection con = super.getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, bookId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private Rating mapRow(ResultSet rs) throws SQLException {   
        Rating r = new Rating();
        r.setId(rs.getInt("id"));
        r.setRating(rs.getInt("rating"));
        r.setReviewText(rs.getString("reviewText"));
        User u = new User();
        u.setId(rs.getInt("userid"));
        r.setUser(u);
        Book b = new Book();
        b.setBookId(rs.getInt("bookid"));
        r.setBook(b);
        return r;
    }
}
