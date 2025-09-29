package vn.binh.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import vn.binh.config.DBConnection;
import vn.binh.dao.BookDAO;
import vn.binh.entity.Book;

public class BookDAOImpl extends DBConnection implements BookDAO {
    @Override
    public List<Book> findAll() {
        String sql = "SELECT bookId, isbn, title, publisher, price, description, publishDate, coverImage, quantity FROM books";
        List<Book> books = new ArrayList<>();
        try {
            Connection con = super.getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    books.add(mapRow(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public Book findById(Integer bookId) {
        String sql = "SELECT bookId, isbn, title, publisher, price, description, publishDate, coverImage, quantity FROM books WHERE bookId = ?";
        try {
            Connection con = super.getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, bookId);
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
    public Book crate(Book book) {
        String sql = "INSERT INTO books(isbn, title, publisher, price, description, publishDate, coverImage, quantity) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection con = super.getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                setParams(ps, book);
                ps.executeUpdate();
                return book;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book update(Book book) {
        String sql = "UPDATE books SET isbn = ?, title = ?, publisher = ?, price = ?, description = ?, publishDate = ?, coverImage = ?, quantity = ? WHERE bookId = ?";
        try {
            Connection con = super.getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                setParams(ps, book);
                ps.setInt(9, book.getBookId());
                ps.executeUpdate();
                return book;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book remove(Integer bookId) {
        Book existing = findById(bookId);
        String sql = "DELETE FROM books WHERE bookId = ?";
        try {
            Connection con = super.getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, bookId);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return existing;
    }

    @Override
    public List<Book> findAll(int pageNumber, int pageSize) {
        String sql = "SELECT bookId, isbn, title, publisher, price, description, publishDate, coverImage, quantity FROM books ORDER BY title ASC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        List<Book> books = new ArrayList<>();
        try {
            Connection con = super.getConnection();
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, (pageNumber - 1) * pageSize);
                ps.setInt(2, pageSize);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        books.add(mapRow(rs));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public long countAll() {
        String sql = "SELECT COUNT(*) FROM books";
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

    private Book mapRow(ResultSet rs) throws SQLException {
        Book b = new Book();
        b.setBookId(rs.getInt("bookId"));
        int isbn = rs.getInt("isbn");
        b.setIsbn(rs.wasNull() ? null : isbn);
        b.setTitle(rs.getString("title"));
        b.setPublisher(rs.getString("publisher"));
        double price = rs.getDouble("price");
        b.setPrice(rs.wasNull() ? null : price);
        b.setDescription(rs.getString("description"));
        java.sql.Date pd = rs.getDate("publishDate");
        if (pd != null) {
            b.setPublishDate(pd.toLocalDate());
        }
        b.setCoverImage(rs.getString("coverImage"));
        int qty = rs.getInt("quantity");
        b.setQuantity(rs.wasNull() ? null : qty);
        return b;
    }

    private void setParams(PreparedStatement ps, Book book) throws SQLException {
        if (book.getIsbn() == null)
            ps.setNull(1, Types.INTEGER);
        else
            ps.setInt(1, book.getIsbn());
        ps.setString(2, book.getTitle());
        ps.setString(3, book.getPublisher());
        if (book.getPrice() == null)
            ps.setNull(4, Types.DECIMAL);
        else
            ps.setDouble(4, book.getPrice());
        ps.setString(5, book.getDescription());
        if (book.getPublishDate() == null)
            ps.setNull(6, Types.DATE);
        else
            ps.setDate(6, java.sql.Date.valueOf(book.getPublishDate()));
        ps.setString(7, book.getCoverImage());
        if (book.getQuantity() == null)
            ps.setNull(8, Types.INTEGER);
        else
            ps.setInt(8, book.getQuantity());
    }
}
