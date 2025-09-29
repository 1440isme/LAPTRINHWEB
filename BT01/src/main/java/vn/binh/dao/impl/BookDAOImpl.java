package vn.binh.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.hibernate.query.Page;
import vn.binh.config.JPAConfig;
import vn.binh.dao.BookDAO;
import vn.binh.entity.Book;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    @Override
    public List<Book> findAll() {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT DISTINCT b FROM Book b LEFT JOIN FETCH b.authors";
            TypedQuery<Book> query = enma.createQuery(jpql, Book.class);
            List<Book> books = query.getResultList();
            System.out.println("Found " + (books != null ? books.size() : 0) + " books in database");
            return books != null ? books : new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Error fetching books from database: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            if (enma != null && enma.isOpen()) {
                enma.close();
            }
        }
    }

    @Override
    public Book findById(Integer bookId) {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            return enma.find(Book.class, bookId); // Sử dụng Integer trực tiếp, không convert thành Long
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
    public Book crate(Book book) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.persist(book);
            trans.commit();
            return book;
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
    public Book update(Book book) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.merge(book);
            trans.commit();
            return book;
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
    public Book remove(Integer bookId) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            Book book = enma.find(Book.class, bookId);
            enma.remove(book);
            trans.commit();
            return book;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            enma.close();
        }
    }

    @Override
    public List<Book> findAll(int pageNumber, int pageSize) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            String jpql = "SELECT DISTINCT b FROM Book b ORDER BY b.title ASC";
            TypedQuery<Book> query = enma.createQuery(jpql, Book.class);
            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public long countAll() {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT COUNT(b) FROM Book b";
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
