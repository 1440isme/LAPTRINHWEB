package vn.binh.service;

import vn.binh.entity.Book;

import java.util.List;

public interface IBookService {
    public List<Book> findAll();
    Book getBook(Integer bookId);
    void insert(Book book);
    void edit(Book book);
    void delete(int id);
    List<Book> findAll(int pageNumber, int pageSize);
    long countAll();

}
