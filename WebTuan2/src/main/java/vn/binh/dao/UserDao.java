package vn.binh.dao;

import vn.binh.models.UserModel;

public interface UserDao {
	UserModel findByUsername(String username);

	void insert(UserModel user);

	boolean checkEmailExist(String email);

	boolean checkUsernameExist(String username);
}
