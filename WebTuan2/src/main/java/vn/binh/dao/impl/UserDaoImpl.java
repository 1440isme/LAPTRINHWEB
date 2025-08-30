package vn.binh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vn.binh.configs.DBConnect;
import vn.binh.dao.UserDao;
import vn.binh.models.UserModel;

public class UserDaoImpl implements UserDao {

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public UserModel findByUsername(String username) {
		String sql = "SELECT * FROM Users WHERE username = ?";
		try {
			conn = new DBConnect().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setFullname(rs.getString("fullname"));
				user.setEmail(rs.getString("email"));

				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(UserModel user) {
		String sqlString = "INSERT INTO Users(username, password, fullname, email) VALUES(?, ?, ?, ?)";
		try {
			conn = new DBConnect().getConnection();
			ps = conn.prepareStatement(sqlString);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFullname());
			ps.setString(4, user.getEmail());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean checkEmailExist(String email) {
		boolean duplicate = false;
		String queryString = "SELECT * FROM Users WHERE email = ?";
		try {
			conn = new DBConnect().getConnection();
			ps = conn.prepareStatement(queryString);
			ps.setString(1, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				duplicate = true;
			}
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return duplicate;
	}

	@Override
	public boolean checkUsernameExist(String username) {
		boolean duplicate = false;
		String queryString = "SELECT * FROM Users WHERE username = ?";
		try {
			conn = new DBConnect().getConnection();
			ps = conn.prepareStatement(queryString);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				duplicate = true;
			}
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return duplicate;
	}

}
