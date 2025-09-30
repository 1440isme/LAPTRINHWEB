package vn.binh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import vn.binh.config.DBConnection;
import vn.binh.dao.UserDAO;
import vn.binh.entity.User;

public class UserDAOImpl extends DBConnection implements UserDAO {

    @Override
    public User create(User user) {
        String sql = "INSERT INTO users(email, passwd, fullname, phone, signupDate, isAdmin) VALUES(?, ?, ?, ?, ?, ?)";
        try {
            Connection con = super.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFullname());
            if (user.getPhone() == null) {
                ps.setNull(4, java.sql.Types.INTEGER);
            } else {
                ps.setInt(4, user.getPhone());
            }
            Timestamp now = Timestamp.valueOf(LocalDateTime.now());
            ps.setTimestamp(5, now);
            ps.setBoolean(6, user.getIsAdmin() != null ? user.getIsAdmin() : false);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User update(User user) {
        String sql = "UPDATE users SET email = ?, passwd = ?, fullname = ?, phone = ?, isAdmin = ? WHERE id = ?";
        try {
            Connection con = super.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFullname());
            if (user.getPhone() == null) {
                ps.setNull(4, java.sql.Types.INTEGER);
            } else {
                ps.setInt(4, user.getPhone());
            }
            ps.setBoolean(5, user.getIsAdmin() != null ? user.getIsAdmin() : false);
            ps.setInt(6, user.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User remove(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try {
            Connection con = super.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findById(int id) {
        String sql = "SELECT TOP 1 id, email, passwd, fullname, phone, signupDate, lastLogin, isAdmin FROM users WHERE id = ?";
        try {
            Connection con = super.getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return mapRowToUser(rs);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT id, email, passwd, fullname, phone, signupDate, lastLogin, isAdmin FROM users";
        List<User> users = new ArrayList<>();
        try {
            Connection con = super.getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    users.add(mapRowToUser(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public List<User> search(String keyword) {
        String sql = "SELECT id, email, passwd, fullname, phone, signupDate, lastLogin, isAdmin FROM users "
                + "WHERE email LIKE ? OR fullname LIKE ? OR CAST(phone AS VARCHAR(50)) LIKE ?";
        List<User> users = new ArrayList<>();
        try {
            Connection con = super.getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                String kw = "%" + (keyword == null ? "" : keyword.trim()) + "%";
                ps.setString(1, kw);
                ps.setString(2, kw);
                ps.setString(3, kw);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        users.add(mapRowToUser(rs));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User login(String email, String password) {
        String sql = "SELECT TOP 1 id, email, passwd, fullname, phone, signupDate, lastLogin, isAdmin FROM users WHERE email = ? AND passwd = ?";
        try {
            Connection con = super.getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, email);
                ps.setString(2, password);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return mapRowToUser(rs);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean checkEmailExist(String email) {
        String sql = "SELECT 1 FROM users WHERE email = ?";
        try {
            Connection con = super.getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, email);
                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updatePasswordByEmail(String email, String newPassword) {
        String sql = "UPDATE users SET passwd = ? WHERE email = ?";
        try {
            Connection con = super.getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, newPassword);
                ps.setString(2, email);
                int updated = ps.executeUpdate();
                return updated > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User findByEmail(String email) {
        String sql = "SELECT TOP 1 id, email, passwd, fullname, phone, signupDate, lastLogin, isAdmin FROM users WHERE email = ?";
        try {
            Connection con = super.getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, email);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return mapRowToUser(rs);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private User mapRowToUser(ResultSet rs) throws Exception {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("passwd"));
        user.setFullname(rs.getString("fullname"));
        int phoneVal = rs.getInt("phone");
        if (rs.wasNull()) {
            user.setPhone(null);
        } else {
            user.setPhone(phoneVal);
        }
        Timestamp signupTs = null;
        try {
            signupTs = rs.getTimestamp("signupDate");
        } catch (Exception ignored) {
        }
        if (signupTs != null) {
            user.setSignupDate(signupTs.toLocalDateTime());
        }
        Timestamp lastLoginTs = null;
        try {
            lastLoginTs = rs.getTimestamp("lastLogin");
        } catch (Exception ignored) {
        }
        if (lastLoginTs != null) {
            user.setLastLogin(lastLoginTs.toLocalDateTime());
        }
        boolean isAdminVal = false;
        try {
            isAdminVal = rs.getBoolean("isAdmin");
        } catch (Exception ignored) {
        }
        user.setIsAdmin(isAdminVal);
        return user;
    }

}