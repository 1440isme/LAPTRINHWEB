package vn.binh.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.binh.config.JPAConfig;
import vn.binh.dao.AuthorDAO;
import vn.binh.entity.Author;

import java.util.ArrayList;
import java.util.List;

public class AuthorDAOImpl implements AuthorDAO {
    @Override
    public List<Author> findAll() {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT DISTINCT a FROM Author a LEFT JOIN FETCH a.books ORDER BY a.authorName ASC";
            TypedQuery<Author> query = enma.createQuery(jpql, Author.class);
            List<Author> authors = query.getResultList();
            System.out.println("Found " + (authors != null ? authors.size() : 0) + " authors in database");
            return authors != null ? authors : new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Error fetching authors from database: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            if (enma != null && enma.isOpen()) {
                enma.close();
            }
        }
    }

    @Override
    public Author findById(Integer authorId) {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT a FROM Author a LEFT JOIN FETCH a.books WHERE a.authorId = :authorId";
            TypedQuery<Author> query = enma.createQuery(jpql, Author.class);
            query.setParameter("authorId", authorId);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (enma != null && enma.isOpen()) {
                enma.close();
            }
        }
    }

    @Override
    public Author create(Author author) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.persist(author);
            trans.commit();
            return author;
        } catch (Exception e) {
            e.printStackTrace();
            if (trans.isActive()) {
                trans.rollback();
            }
            return null;
        } finally {
            enma.close();
        }
    }

    @Override
    public Author update(Author author) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.merge(author);
            trans.commit();
            return author;
        } catch (Exception e) {
            e.printStackTrace();
            if (trans.isActive()) {
                trans.rollback();
            }
            return null;
        } finally {
            enma.close();
        }
    }

    @Override
    public Author remove(Integer authorId) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            Author author = enma.find(Author.class, authorId);
            if (author != null) {
                enma.remove(author);
            }
            trans.commit();
            return author;
        } catch (Exception e) {
            e.printStackTrace();
            if (trans.isActive()) {
                trans.rollback();
            }
            return null;
        } finally {
            enma.close();
        }
    }

    @Override
    public List<Author> findAll(int pageNumber, int pageSize) {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT DISTINCT a FROM Author a LEFT JOIN FETCH a.books ORDER BY a.authorName ASC";
            TypedQuery<Author> query = enma.createQuery(jpql, Author.class);
            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            if (enma != null && enma.isOpen()) {
                enma.close();
            }
        }
    }

    @Override
    public long countAll() {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT COUNT(a) FROM Author a";
            TypedQuery<Long> query = em.createQuery(jpql, Long.class);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
