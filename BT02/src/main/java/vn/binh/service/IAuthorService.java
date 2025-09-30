package vn.binh.service;

import vn.binh.entity.Author;

import java.util.List;

public interface IAuthorService {
    List<Author> findAll();
    Author getAuthor(Integer authorId);
    void insert(Author author);
    void edit(Author author);
    void delete(int id);
    List<Author> findAll(int pageNumber, int pageSize);
    long countAll();
}
