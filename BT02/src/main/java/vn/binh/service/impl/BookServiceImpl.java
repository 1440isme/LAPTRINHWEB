package vn.binh.service.impl;

import vn.binh.dao.BookDAO;
import vn.binh.dao.impl.BookDAOImpl;
import vn.binh.entity.Book;
import vn.binh.service.IBookService;

import java.util.List;


public class BookServiceImpl implements IBookService {
    BookDAO bookDAO = new BookDAOImpl();
    @Override
    public List<Book> findAll() {
        return bookDAO.findAll();
    }

    @Override
    public Book getBook(Integer bookId) {
        return bookDAO.findById(bookId);
    }

    @Override
    public void insert(Book book) {
        bookDAO.crate(book);
    }

    @Override
    public void edit(Book book) {
        bookDAO.update(book);
    }

    @Override
    public void delete(int id) {
        bookDAO.remove(id);
    }

    @Override
    public List<Book> findAll(int pageNumber, int pageSize) {
        return bookDAO.findAll(pageNumber, pageSize);
    }

    @Override
    public long countAll() {
        return bookDAO.countAll();
    }
}
