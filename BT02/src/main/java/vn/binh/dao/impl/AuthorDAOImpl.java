package vn.binh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.binh.config.DBConnection;
import vn.binh.dao.AuthorDAO;
import vn.binh.entity.Author;

public class AuthorDAOImpl extends DBConnection implements AuthorDAO {
    @Override
    public List<Author> findAll() {
        String sql = "SELECT authorId, authorName, dateOfBirth FROM author ORDER BY authorName ASC";
        List<Author> authors = new ArrayList<>();
        try {
            Connection con = super.getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    authors.add(mapRow(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authors;
    }

    @Override
    public Author findById(Integer authorId) {
        String sql = "SELECT authorId, authorName, dateOfBirth FROM author WHERE authorId = ?";
        try {
            Connection con = super.getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, authorId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return mapRow(rs);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Author create(Author author) {
        String sql = "INSERT INTO author(authorName, dateOfBirth) VALUES(?, ?)";
        try {
            Connection con = super.getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, author.getAuthorName());
                if (author.getDateOfBirth() == null) {
                    ps.setNull(2, java.sql.Types.DATE);
                } else {
                    ps.setDate(2, java.sql.Date.valueOf(author.getDateOfBirth()));
                }
                ps.executeUpdate();
            }
            return author;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Author update(Author author) {
        String sql = "UPDATE author SET authorName = ?, dateOfBirth = ? WHERE authorId = ?";
        try {
            Connection con = super.getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, author.getAuthorName());
                if (author.getDateOfBirth() == null) {
                    ps.setNull(2, java.sql.Types.DATE);
                } else {
                    ps.setDate(2, java.sql.Date.valueOf(author.getDateOfBirth()));
                }
                ps.setInt(3, author.getAuthorId());
                ps.executeUpdate();
                return author;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Author remove(Integer authorId) {
        Author existing = findById(authorId);
        String sql = "DELETE FROM author WHERE authorId = ?";
        try {
            Connection con = super.getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, authorId);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return existing;
    }

    @Override
    public List<Author> findAll(int pageNumber, int pageSize) {
        String sql = "SELECT authorId, authorName, dateOfBirth FROM author ORDER BY authorName ASC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        List<Author> authors = new ArrayList<>();
        try {
            Connection con = super.getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, (pageNumber - 1) * pageSize);
                ps.setInt(2, pageSize);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        authors.add(mapRow(rs));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authors;
    }

    @Override
    public long countAll() {
        String sql = "SELECT COUNT(*) FROM author";
        try {
            Connection con = super.getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private Author mapRow(ResultSet rs) throws SQLException {
        Author a = new Author();
        a.setAuthorId(rs.getInt("authorId"));
        a.setAuthorName(rs.getString("authorName"));
        java.sql.Date dob = rs.getDate("dateOfBirth");
        if (dob != null) {
            a.setDateOfBirth(dob.toLocalDate());
        }
        return a;
    }
}
