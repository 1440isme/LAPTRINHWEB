package vn.binh.dao;

import java.util.List;

import vn.binh.entity.User;

public interface UserDAO {
	User create(User user);

	User update(User user);

	User remove(int id);

	User findById(int id);

	User findByUsername(String username);

	List<User> findAll();

	List<User> search(String keyword);

	User login(String username, String password);

	boolean checkEmailExist(String email);

	boolean checkUsernameExist(String username);

	boolean updatePasswordByEmail(String email, String newPassword);

	User findByEmail(String email);
}