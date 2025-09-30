package vn.binh.dao;

import vn.binh.entity.Author;

import java.util.List;

public interface AuthorDAO {
    List<Author> findAll();
    Author findById(Integer authorId);
    Author create(Author author);
    Author update(Author author);
    Author remove(Integer authorId);
    List<Author> findAll(int pageNumber, int pageSize);
    long countAll();
}
