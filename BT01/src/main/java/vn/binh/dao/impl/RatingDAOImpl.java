package vn.binh.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import vn.binh.config.JPAConfig;
import vn.binh.dao.RatingDAO;
import vn.binh.entity.Rating;

import java.util.ArrayList;
import java.util.List;

public class RatingDAOImpl implements RatingDAO {
    @Override
    public List<Rating> findByBookId(Integer bookId) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            TypedQuery<Rating> query = em.createQuery(
                    "SELECT r FROM Rating r JOIN FETCH r.user JOIN FETCH r.book WHERE r.book.bookId = :bookId", Rating.class);
            query.setParameter("bookId", bookId); // Sử dụng Integer trực tiếp, không convert thành Long
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error finding ratings by bookId " + bookId + ": " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public void insert(Rating rating) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            em.getTransaction().begin();

            // Generate a unique ID for the rating since database doesn't auto-increment
            Integer newId = generateNextId(em);
            rating.setId(newId);

            System.out.println("Persisting rating with ID: " + newId + " for user: " +
                rating.getUser().getId() + ", book: " + rating.getBook().getBookId());

            em.persist(rating);
            em.getTransaction().commit();

            System.out.println("Rating persisted successfully");

        } catch (Exception e) {
            System.err.println("Error inserting rating: " + e.getMessage());
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Failed to insert rating: " + e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    private Integer generateNextId(EntityManager em) {
        try {
            TypedQuery<Integer> query = em.createQuery(
                "SELECT MAX(r.id) FROM Rating r", Integer.class);
            Integer maxId = query.getSingleResult();
            return (maxId != null) ? maxId + 1 : 1;
        } catch (Exception e) {
            System.err.println("Error generating next ID: " + e.getMessage());
            return 1; // Start with ID 1 if no records exist
        }
    }

    @Override
    public int countByBookId(Integer bookId) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(r) FROM Rating r WHERE r.book.bookId = :bookId", Long.class);
            query.setParameter("bookId", bookId);
            Long count = query.getSingleResult();
            return count != null ? count.intValue() : 0;
        } catch (Exception e) {
            System.err.println("Error counting ratings by bookId " + bookId + ": " + e.getMessage());
            e.printStackTrace();
            return 0;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
