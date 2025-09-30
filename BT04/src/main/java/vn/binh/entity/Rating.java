package vn.binh.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "rating")
@NamedQuery(name = "Rating.findAll", query = "SELECT r FROM Rating r")
public class Rating implements Serializable {
    /**
    * 
    */
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private RatingId id;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "reviewText")
    private String reviewText;

    @ManyToOne
    @MapsId("userid")
    @JoinColumn(name = "userid")
    private User user;

    @ManyToOne
    @MapsId("bookid")
    @JoinColumn(name = "bookid")
    private Book book;

}
