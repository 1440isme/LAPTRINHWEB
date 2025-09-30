package vn.binh.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.binh.repositoty.UserRepository_23110184;
import vn.binh.entity.User_23110184;
import vn.binh.service.UserService_23110184;

@Service
public class UserServiceImpl_23110184 implements UserService_23110184 {

	private final UserRepository_23110184 userRepository;

	public UserServiceImpl_23110184(UserRepository_23110184 userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void insert(User_23110184 user) {
		userRepository.save(user);
	}

	@Override
	public void edit(User_23110184 user) {
		userRepository.save(user);
	}

	@Override
	public void delete(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public List<User_23110184> getAll() {
		return userRepository.findAll();
	}

	@Override
	public Page<User_23110184> getAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public List<User_23110184> search(String keyword) {
		return userRepository.findByFullnameContainingIgnoreCase(keyword);
	}

	@Override
	public User_23110184 getIdUser(int id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public User_23110184 login(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	public User_23110184 findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public boolean register(String username, String email, String password, String fullname, Integer phone) {
		if (checkUsernameExist(username)) {
			return false;
		}

		// Tạo user mới với phone (mặc định là User, không có sellerId)
		User_23110184 user = new User_23110184();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);
		user.setFullname(fullname);
		user.setPhone(phone);

		try {
			userRepository.save(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean checkUsernameExist(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public User_23110184 findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}