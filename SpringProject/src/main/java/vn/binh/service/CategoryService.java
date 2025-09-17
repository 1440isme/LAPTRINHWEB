package vn.binh.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import vn.binh.entity.Category;
import vn.binh.repositoty.CategoryRepository;

public interface CategoryService {
	@Autowired
	CategoryRepository categoryRepository;

	void deleteAll();

	void deleteAll(Iterable<? extends Category> entities);

	<S extends Category> List<S> findAll(Example<S> example, Sort sort);

	<S extends Category> List<S> findAll(Example<S> example);

	void deleteAllById(Iterable<? extends Integer> ids);

	Category getReferenceById(Integer id);

	void delete(Category entity);

	void deleteById(Integer id);

	Category getById(Integer id);

	long count();

	<S extends Category, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	Category getOne(Integer id);

	void deleteAllInBatch();

	<S extends Category> boolean exists(Example<S> example);

	boolean existsById(Integer id);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	<S extends Category> long count(Example<S> example);

	Optional<Category> findById(Integer id);

	void deleteAllInBatch(Iterable<Category> entities);

	<S extends Category> Page<S> findAll(Example<S> example, org.springframework.data.domain.Pageable pageable);

	void deleteInBatch(Iterable<Category> entities);

	List<Category> findAllById(Iterable<Integer> ids);

	List<Category> findAll();

	<S extends Category> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Category> S saveAndFlush(S entity);

	Page<Category> findAll(org.springframework.data.domain.Pageable pageable);

	void flush();

	<S extends Category> S save(S entity);

	List<Category> findAll(Sort sort);

	<S extends Category> Optional<S> findOne(Example<S> example);

	<S extends Category> List<S> saveAll(Iterable<S> entities);

	Page<Category> findByCategoryNameContaining(String name, Pageable pageable);

	List<Category> findByCategoryNameContaining(String name);

}
