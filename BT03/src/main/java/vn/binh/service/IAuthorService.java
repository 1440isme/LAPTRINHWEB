package vn.binh.service;

import vn.binh.entity.Author;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface IAuthorService {
    List<Author> findAll();

    Author getAuthor(Integer authorId);

    void insert(Author author);

    void edit(Author author);

    void delete(int id);

    Page<Author> findAll(Pageable pageable);
}
