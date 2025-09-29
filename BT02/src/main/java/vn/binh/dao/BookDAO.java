package vn.binh.dao;

import vn.binh.entity.Book;

import java.util.List;

public interface BookDAO {
    public List<Book> findAll();
    Book findById(Integer bookId);

    Book crate(Book book);
    Book update(Book book);
    Book remove(Integer bookId);
    List<Book> findAll(int pageNumber, int pageSize);
    long countAll();
}

