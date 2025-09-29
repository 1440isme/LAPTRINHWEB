package vn.binh.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.binh.repositoty.UserRepository;
import vn.binh.entity.User;
import vn.binh.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void insert(User user) {
		userRepository.save(user);
	}

	@Override
	public void edit(User user) {
		userRepository.save(user);
	}

	@Override
	public void delete(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public List<User> search(String keyword) {
		return userRepository.findByFullnameContainingIgnoreCase(keyword);
	}

	@Override
	public User getIdUser(int id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User login(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public boolean register(String email, String password, String fullname) {
		if (checkEmailExist(email)) {
			return false;
		}

		// Tạo user mới
		User user = new User();
		user.setEmail(email); // Set email field
		user.setPassword(password);
		user.setFullname(fullname);
		user.setPhone(null);
		user.setIsAdmin(false); // Role mặc định là user (không phải admin)

		try {
			userRepository.save(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean register(String email, String password, String fullname, Integer phone) {
		if (checkEmailExist(email)) {
			return false;
		}

		// Tạo user mới với phone
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setFullname(fullname);
		user.setPhone(phone); // Set phone field
		user.setIsAdmin(false); // Role mặc định là user (không phải admin)

		try {
			userRepository.save(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean checkEmailExist(String email) {
		return userRepository.existsByEmail(email);
	}

}