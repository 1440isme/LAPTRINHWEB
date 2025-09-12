package vn.binh.service;

import java.util.List;

import vn.binh.entity.User;

public interface UserService {
	void insert(User user);

	void edit(User user);

	void delete(int id);

	User getIdUser(int id);

	User getUsernameUser(String username);

	List<User> getAll();

	List<User> search(String keyword);

	User login(String username, String password);

	User findByUsername(String username);

	User findByEmail(String email);

	boolean register(String email, String password, String username, String fullname);

	boolean register(String email, String password, String username, String fullname, String phone);

	boolean checkEmailExist(String email);

	boolean checkUsernameExist(String username);
}