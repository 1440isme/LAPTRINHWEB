package vn.binh.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.binh.entity.User_23110184;

public interface UserService_23110184 {
	void insert(User_23110184 user);

	void edit(User_23110184 user);

	void delete(int id);

	User_23110184 getIdUser(int id);

	List<User_23110184> getAll();

	Page<User_23110184> getAll(Pageable pageable);

	List<User_23110184> search(String keyword);

	User_23110184 login(String username, String password);

	User_23110184 findByUsername(String username);

	boolean register(String username, String email, String password, String fullname, Integer phone);

	boolean checkUsernameExist(String username);

	User_23110184 findByEmail(String email);

}