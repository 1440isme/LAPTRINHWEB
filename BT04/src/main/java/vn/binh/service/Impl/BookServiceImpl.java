package vn.binh.service.Impl;

import vn.binh.repositoty.BookRepository;
import vn.binh.entity.Book;
import vn.binh.service.IBookService;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements IBookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBook(Integer bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    @Override
    public void insert(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void edit(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

}
