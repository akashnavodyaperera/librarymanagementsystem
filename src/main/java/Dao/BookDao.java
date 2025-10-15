package Dao;

import Model.Book;
import Database.DBConnection;
import java.sql.*;
import java.util.*;

public class BookDao {
    
    //Add new book
    public boolean addBook(Book book) {
        String sql = "INSERT INTO books (title, author, category, isbn, publisher, publication_year, availability) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getCategory());
            stmt.setString(4, book.getIsbn());
            stmt.setString(5, book.getPublisher());
            stmt.setInt(6, book.getPublicationYear());
            stmt.setBoolean(7, book.isAvailability());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //Get all books
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                books.add(new Book(
                    rs.getInt("book_id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("category"),
                    rs.getString("isbn"),
                    rs.getString("publisher"),
                    rs.getInt("publication_year"),
                    rs.getBoolean("availability")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    
    //Search books
    public List<Book> searchBooks(String keyword) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ? OR isbn LIKE ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            String searchPattern = "%" + keyword + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            stmt.setString(3, searchPattern);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    books.add(new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getString("isbn"),
                        rs.getString("publisher"),
                        rs.getInt("publication_year"),
                        rs.getBoolean("availability")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    
    //Get available books
    public List<Book> getAvailableBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE availability = TRUE";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                books.add(new Book(
                    rs.getInt("book_id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("category"),
                    rs.getString("isbn"),
                    rs.getString("publisher"),
                    rs.getInt("publication_year"),
                    rs.getBoolean("availability")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    
    //Get book by ISBN
    public Book getBookByIsbn(String isbn) {
        String sql = "SELECT * FROM books WHERE isbn = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, isbn);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getString("isbn"),
                        rs.getString("publisher"),
                        rs.getInt("publication_year"),
                        rs.getBoolean("availability")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //Get book by ID
    public Book getBookById(int bookId) {
        String sql = "SELECT * FROM books WHERE book_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getString("isbn"),
                        rs.getString("publisher"),
                        rs.getInt("publication_year"),
                        rs.getBoolean("availability")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //Update existing book by ISBN
    public boolean updateBook(Book book) {
        String sql = "UPDATE books SET title = ?, author = ?, category = ?, publisher = ?, publication_year = ?, availability = ? WHERE isbn = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getCategory());
            stmt.setString(4, book.getPublisher());
            stmt.setInt(5, book.getPublicationYear());
            stmt.setBoolean(6, book.isAvailability());
            stmt.setString(7, book.getIsbn());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //Update book availability by book ID
    public boolean updateBookAvailability(int bookId, boolean availability) {
        String sql = "UPDATE books SET availability = ? WHERE book_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, availability);
            stmt.setInt(2, bookId);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //Delete book by ISBN
    public boolean deleteBook(String isbn) {
        String sql = "DELETE FROM books WHERE isbn = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, isbn);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
      public int getTotalBooksCount() {
        String sql = "SELECT COUNT(*) as total FROM books";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            if (rs.next()) {
                return rs.getInt("total");
            }
            
        } catch (SQLException e) {
            System.out.println("Error getting total books count: " + e.getMessage());
            e.printStackTrace();
        }
        
        return 0;
    }
}