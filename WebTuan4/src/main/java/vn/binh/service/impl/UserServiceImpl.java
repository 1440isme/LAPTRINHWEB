package vn.binh.service.impl;

import java.util.List;

import vn.binh.dao.UserDAO;
import vn.binh.dao.impl.UserDAOImpl;
import vn.binh.entity.User;
import vn.binh.service.UserService;

public class UserServiceImpl implements UserService {

	UserDAO userDAO = new UserDAOImpl();

	@Override
	public void insert(User user) {
		userDAO.create(user);
	}

	@Override
	public void edit(User user) {
		userDAO.update(user);
	}

	@Override
	public void delete(int id) {
		userDAO.remove(id);
	}

	@Override
	public List<User> getAll() {
		return userDAO.findAll();
	}

	@Override
	public List<User> search(String keyword) {
		return userDAO.search(keyword);
	}

	@Override
	public User getIdUser(int id) {
		return userDAO.findById(id);
	}

	@Override
	public User getUsernameUser(String username) {
		return userDAO.findByUsername(username);
	}

	@Override
	public User login(String username, String password) {
		return userDAO.login(username, password);
	}

	@Override
	public User findByUsername(String username) {
		return userDAO.findByUsername(username);
	}

	@Override
	public boolean register(String email, String password, String username, String fullname) {
		if (checkUsernameExist(username)) {
			return false;
		}

		if (checkEmailExist(email)) {
			return false;
		}

		// Tạo user mới
		User user = new User();
		user.setName(username);
		user.setPassword(password);
		user.setFullname(fullname);
		user.setPhone(""); // Có thể để trống
		user.setImages(""); // Có thể để trống
		user.setRole(2); // Role mặc định là user (không phải admin)

		try {
			userDAO.create(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean checkEmailExist(String email) {
		// Implement check email exist logic
		// For now, return false since we don't have email field in User entity
		return false;
	}

	@Override
	public boolean checkUsernameExist(String username) {
		User user = userDAO.findByUsername(username);
		return user != null;
	}
}