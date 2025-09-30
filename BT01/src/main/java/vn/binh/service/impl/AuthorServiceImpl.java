package vn.binh.service.impl;

import vn.binh.dao.AuthorDAO;
import vn.binh.dao.impl.AuthorDAOImpl;
import vn.binh.entity.Author;
import vn.binh.service.IAuthorService;

import java.util.List;

public class AuthorServiceImpl implements IAuthorService {
    AuthorDAO authorDAO = new AuthorDAOImpl();

    @Override
    public List<Author> findAll() {
        return authorDAO.findAll();
    }

    @Override
    public Author getAuthor(Integer authorId) {
        return authorDAO.findById(authorId);
    }

    @Override
    public void insert(Author author) {
        authorDAO.create(author);
    }

    @Override
    public void edit(Author author) {
        authorDAO.update(author);
    }

    @Override
    public void delete(int id) {
        authorDAO.remove(id);
    }

    @Override
    public List<Author> findAll(int pageNumber, int pageSize) {
        return authorDAO.findAll(pageNumber, pageSize);
    }

    @Override
    public long countAll() {
        return authorDAO.countAll();
    }
}
