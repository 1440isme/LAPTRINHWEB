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
	public User login(String email, String password) {
		return userDAO.login(email, password);
	}


	@Override
	public User findByEmail(String email) {
		return userDAO.findByEmail(email);
	}

	@Override
	public boolean register(String email, String password, String fullname) {
		if (checkEmailExist(email)) {
			return false;
		}

		if (checkEmailExist(email)) {
			return false;
		}

		// Tạo user mới
		User user = new User();
		user.setEmail(email);  // Set email field
		user.setPassword(password);
		user.setFullname(fullname);
		user.setPhone(null);
		user.setIsAdmin(false); // Role mặc định là user (không phải admin)

		try {
			userDAO.create(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
    public boolean register(String email, String password,  String fullname, Integer phone) {
		if (checkEmailExist(email)) {
			return false;
		}

		if (checkEmailExist(email)) {
			return false;
		}

		// Tạo user mới với phone
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setFullname(fullname);
		user.setPhone(phone);  // Set phone field
		user.setIsAdmin(false); // Role mặc định là user (không phải admin)

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
		return userDAO.checkEmailExist(email);
	}


}