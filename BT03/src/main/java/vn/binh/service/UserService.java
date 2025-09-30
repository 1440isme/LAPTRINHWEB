package vn.binh.service;

import java.util.List;

import vn.binh.entity.User;

public interface UserService {
	void insert(User user);

	void edit(User user);

	void delete(int id);

	User getIdUser(int id);


	List<User> getAll();

	List<User> search(String keyword);

	User login(String email, String password);


	User findByEmail(String email);

	boolean register(String email, String password,  String fullname);

	boolean register(String email, String password, String fullname, Integer phone);


    boolean checkEmailExist(String email);

}