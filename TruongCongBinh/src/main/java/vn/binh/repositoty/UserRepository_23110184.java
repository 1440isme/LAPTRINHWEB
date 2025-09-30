package vn.binh.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

import org.springframework.stereotype.Repository;

import vn.binh.entity.User_23110184;

@Repository
public interface UserRepository_23110184 extends JpaRepository<User_23110184, Integer> {

    User_23110184 findByEmail(String email);

    User_23110184 findByUsername(String username);

    boolean existsByUsername(String username);

    User_23110184 findByUsernameAndPassword(String username, String password);

    List<User_23110184> findByFullnameContainingIgnoreCase(String keyword);

    Page<User_23110184> findAll(Pageable pageable);
}
