package vn.binh.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.binh.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
