package vn.binh.entity;

import java.io.Serializable;

public class Rating implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer rating;
    private String reviewText;
    private User user;
    private Book book;

    public Rating() {
    }

    public Rating(Integer id, Integer rating, String reviewText) {
        this.id = id;
        this.rating = rating;
        this.reviewText = reviewText;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
