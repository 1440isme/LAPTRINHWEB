package vn.binh.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {
	private Integer id;
	private String email;
	private String password;
	private String fullname;
	private Integer phone;
	private LocalDateTime signupDate;
	private LocalDateTime lastLogin;
	private Boolean isAdmin;
	private List<Rating> ratings = new ArrayList<>();

	public User() {
	}

	public User(Integer id, String email, String password, String fullname) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public LocalDateTime getSignupDate() {
		return signupDate;
	}

	public void setSignupDate(LocalDateTime signupDate) {
		this.signupDate = signupDate;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
}