package vn.binh.service.Impl;

import vn.binh.repositoty.AuthorRepository;
import vn.binh.entity.Author;
import vn.binh.service.IAuthorService;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements IAuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthor(Integer authorId) {
        return authorRepository.findById(authorId).orElse(null);
    }

    @Override
    public void insert(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void edit(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void delete(int id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Page<Author> findAll(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

}
