package vn.binh.service;

import vn.binh.entity.Book;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface IBookService {
    public List<Book> findAll();

    Book getBook(Integer bookId);

    void insert(Book book);

    void edit(Book book);

    void delete(int id);

    Page<Book> findAll(Pageable pageable);

}
