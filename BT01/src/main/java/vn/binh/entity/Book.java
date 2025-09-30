package vn.binh.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "books")
@NamedQuery(name = "Book.findAll", query = "SELECT DISTINCT b FROM Book b LEFT JOIN FETCH b.authors")
public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookId;
    private  Integer isbn;
    @Column(name = "title", columnDefinition = "varchar(200)")
    private String title;
    @Column(name = "publisher", columnDefinition = "varchar(100)")
    private String publisher;
    @Column(name = "price", columnDefinition = "decimal(6,2)")
    private Double  price;
    @Column(name = "description")
    private String description;
    private LocalDate publishDate;
    private String coverImage;
    private  Integer quantity;

    @Transient
    private Integer ratingCount = 0;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<Rating> ratings = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "bookid"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors = new ArrayList<>();

}