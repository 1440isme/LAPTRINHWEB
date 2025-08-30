package vn.binh.services.impl;

import vn.binh.dao.UserDao;
import vn.binh.dao.impl.UserDaoImpl;
import vn.binh.models.UserModel;
import vn.binh.services.UserService;

public class UserServiceImpl implements UserService {
	UserDao userDao = new UserDaoImpl();

	@Override
	public UserModel login(String username, String password) {
		UserModel user = this.findByUsername(username);
		if (user != null && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}

	@Override
	public UserModel findByUsername(String username) {

		return userDao.findByUsername(username);
	}

	@Override
	public void insert(UserModel user) {
		userDao.insert(user);
	}

	@Override
	public boolean register(String email, String password, String username, String fullname) {
		if (userDao.checkUsernameExist(username)) {
			return false;
		}

		userDao.insert(new UserModel(0, username, password, fullname, email));
		return true;
	}

	@Override
	public boolean checkEmailExist(String email) {
		return userDao.checkEmailExist(email);
	}

	@Override
	public boolean checkUsernameExist(String username) {
		return userDao.checkUsernameExist(username);
	}

}
