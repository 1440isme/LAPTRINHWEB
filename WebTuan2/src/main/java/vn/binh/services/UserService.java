package vn.binh.services;

import vn.binh.models.UserModel;

public interface UserService {

	UserModel login(String username, String password);

	UserModel findByUsername(String username);

	void insert(UserModel user);

	boolean register(String email, String password, String username, String fullname);

	boolean checkEmailExist(String email);

	boolean checkUsernameExist(String username);

}
