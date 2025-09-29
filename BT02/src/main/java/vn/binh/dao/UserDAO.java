package vn.binh.dao;

import java.util.List;

import vn.binh.entity.User;

public interface UserDAO {
	User create(User user);

	User update(User user);

	User remove(int id);

	User findById(int id);


	List<User> findAll();

	List<User> search(String keyword);

	User login(String email, String password);

	boolean checkEmailExist(String email);


	boolean updatePasswordByEmail(String email, String newPassword);

	User findByEmail(String email);
}