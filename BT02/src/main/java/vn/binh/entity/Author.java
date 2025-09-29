package vn.binh.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Author implements Serializable {
	private static final long serialVersionUID = 1L;

	private int authorId;
	private String authorName;
	private LocalDate dateOfBirth;
	private List<Book> books = new ArrayList<>();

	public Author() {
	}

	public Author(int authorId, String authorName, LocalDate dateOfBirth) {
		this.authorId = authorId;
		this.authorName = authorName;
		this.dateOfBirth = dateOfBirth;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
}
