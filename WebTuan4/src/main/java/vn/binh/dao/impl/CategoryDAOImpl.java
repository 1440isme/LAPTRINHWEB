package vn.binh.dao.impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.binh.config.JPAConfig;
import vn.binh.dao.CategoryDAO;
import vn.binh.entity.Category;

public class CategoryDAOImpl implements CategoryDAO{

	@Override
	public Category create(Category category) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(category);
			trans.commit();
			return category;
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public Category update(Category category) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(category);
			trans.commit();
			return category;
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public Category remove(int id) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		Category category = null;
		try {
			trans.begin();
			category = enma.find(Category.class, id);
			if (category != null) {
				enma.remove(category);
			}
			trans.commit();
			return category;
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public Category findById(int id) {
		EntityManager enma = JPAConfig.getEntityManager();
		Category category = enma.find(Category.class, id);
		enma.close();
		return category;
	}

	@Override
	public List<Category> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
		List<Category> list = query.getResultList();
		enma.close();
		return list;
	}

	@Override
	public Category findByName(String name) {
		EntityManager enma = JPAConfig.getEntityManager();
		try {
			TypedQuery<Category> query = enma.createQuery("SELECT c FROM Category c WHERE c.name = :name", Category.class);
			query.setParameter("name", name);
			List<Category> results = query.getResultList();
			return results.isEmpty() ? null : results.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			enma.close();
		}
	}

	@Override
	public List<Category> search(String keyword) {
		EntityManager enma = JPAConfig.getEntityManager();
		try {
			TypedQuery<Category> query = enma.createQuery("SELECT c FROM Category c WHERE c.name LIKE :keyword", Category.class);
			query.setParameter("keyword", "%" + keyword + "%");
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			enma.close();
		}
	}
}