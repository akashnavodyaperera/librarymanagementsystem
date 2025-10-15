package Dao;

import Model.Borrow;
import Database.DBConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class BorrowDao {
    
    //Add new borrow record
    public boolean addBorrow(Borrow borrow) {
        String sql = "INSERT INTO borrow_records (member_id, book_id, issue_date, due_date, return_date, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, borrow.getMemberId());
            stmt.setInt(2, borrow.getBookId());
            stmt.setDate(3, borrow.getIssueDate());
            stmt.setDate(4, borrow.getDueDate());
            stmt.setDate(5, borrow.getReturnDate());
            stmt.setString(6, borrow.getStatus());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //Get all borrow records
    public List<Borrow> getAllBorrows() {
        List<Borrow> borrows = new ArrayList<>();
        String sql = "SELECT * FROM borrow_records";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                borrows.add(new Borrow(
                    rs.getInt("borrow_id"),
                    rs.getInt("member_id"),
                    rs.getInt("book_id"),
                    rs.getDate("issue_date"),
                    rs.getDate("due_date"),
                    rs.getDate("return_date"),
                    rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrows;
    }
    
    //Get borrow records by member ID
    public List<Borrow> getBorrowsByMemberId(int memberId) {
        List<Borrow> borrows = new ArrayList<>();
        String sql = "SELECT * FROM borrow_records WHERE member_id = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, memberId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    borrows.add(new Borrow(
                        rs.getInt("borrow_id"),
                        rs.getInt("member_id"),
                        rs.getInt("book_id"),
                        rs.getDate("issue_date"),
                        rs.getDate("due_date"),
                        rs.getDate("return_date"),
                        rs.getString("status")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrows;
    }
    
    //Get borrow records
    public List<Borrow> getBorrowsByBookId(int bookId) {
        List<Borrow> borrows = new ArrayList<>();
        String sql = "SELECT * FROM borrow_records WHERE book_id = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    borrows.add(new Borrow(
                        rs.getInt("borrow_id"),
                        rs.getInt("member_id"),
                        rs.getInt("book_id"),
                        rs.getDate("issue_date"),
                        rs.getDate("due_date"),
                        rs.getDate("return_date"),
                        rs.getString("status")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrows;
    }
    
    //Get borrow records
    public Borrow getBorrowById(int borrowId) {
        String sql = "SELECT * FROM borrow_records WHERE borrow_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, borrowId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Borrow(
                        rs.getInt("borrow_id"),
                        rs.getInt("member_id"),
                        rs.getInt("book_id"),
                        rs.getDate("issue_date"),
                        rs.getDate("due_date"),
                        rs.getDate("return_date"),
                        rs.getString("status")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //Get borrow records by status
    public List<Borrow> getBorrowsByStatus(String status) {
        List<Borrow> borrows = new ArrayList<>();
        String sql = "SELECT * FROM borrow_records WHERE status = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    borrows.add(new Borrow(
                        rs.getInt("borrow_id"),
                        rs.getInt("member_id"),
                        rs.getInt("book_id"),
                        rs.getDate("issue_date"),
                        rs.getDate("due_date"),
                        rs.getDate("return_date"),
                        rs.getString("status")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrows;
    }
    
    //Search borrow records by member name or book title
    public List<Map<String, Object>> searchBorrowRecords(String keyword) {
        List<Map<String, Object>> records = new ArrayList<>();
        String sql = "SELECT br.borrow_id, br.member_id, br.book_id, br.issue_date, br.due_date, " +
                     "br.return_date, br.status, m.name as member_name, b.title as book_title " +
                     "FROM borrow_records br " +
                     "JOIN members m ON br.member_id = m.member_id " +
                     "JOIN books b ON br.book_id = b.book_id " +
                     "WHERE m.name LIKE ? OR b.title LIKE ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            String searchPattern = "%" + keyword + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> record = new HashMap<>();
                    record.put("borrow_id", rs.getInt("borrow_id"));
                    record.put("member_id", rs.getInt("member_id"));
                    record.put("book_id", rs.getInt("book_id"));
                    record.put("member_name", rs.getString("member_name"));
                    record.put("book_title", rs.getString("book_title"));
                    record.put("issue_date", rs.getDate("issue_date"));
                    record.put("due_date", rs.getDate("due_date"));
                    record.put("return_date", rs.getDate("return_date"));
                    record.put("status", rs.getString("status"));
                    records.add(record);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }
    
    //Get all borrow records with member and book details
    public List<Map<String, Object>> getAllBorrowRecordsWithDetails() {
        List<Map<String, Object>> records = new ArrayList<>();
        String sql = "SELECT br.borrow_id, br.member_id, br.book_id, br.issue_date, br.due_date, " +
                     "br.return_date, br.status, m.name as member_name, b.title as book_title " +
                     "FROM borrow_records br " +
                     "JOIN members m ON br.member_id = m.member_id " +
                     "JOIN books b ON br.book_id = b.book_id";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Map<String, Object> record = new HashMap<>();
                record.put("borrow_id", rs.getInt("borrow_id"));
                record.put("member_id", rs.getInt("member_id"));
                record.put("book_id", rs.getInt("book_id"));
                record.put("member_name", rs.getString("member_name"));
                record.put("book_title", rs.getString("book_title"));
                record.put("issue_date", rs.getDate("issue_date"));
                record.put("due_date", rs.getDate("due_date"));
                record.put("return_date", rs.getDate("return_date"));
                record.put("status", rs.getString("status"));
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }
    
    //Update existing borrow record
    public boolean updateBorrow(Borrow borrow) {
        String sql = "UPDATE borrow_records SET member_id = ?, book_id = ?, issue_date = ?, due_date = ?, return_date = ?, status = ? WHERE borrow_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, borrow.getMemberId());
            stmt.setInt(2, borrow.getBookId());
            stmt.setDate(3, borrow.getIssueDate());
            stmt.setDate(4, borrow.getDueDate());
            stmt.setDate(5, borrow.getReturnDate());
            stmt.setString(6, borrow.getStatus());
            stmt.setInt(7, borrow.getBorrowId());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //Mark book as returned
    public boolean returnBook(int borrowId, Date returnDate) {
        String sql = "UPDATE borrow_records SET return_date = ?, status = 'returned' WHERE borrow_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, returnDate);
            stmt.setInt(2, borrowId);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //Update status to overdue
    public boolean updateOverdueStatus() {
        String sql = "UPDATE borrow_records SET status = 'overdue' WHERE due_date < CURDATE() AND status = 'borrowed'";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            return stmt.executeUpdate(sql) >= 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    //Delete borrow record by ID
    public boolean deleteBorrow(int borrowId) {
        String sql = "DELETE FROM borrow_records WHERE borrow_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, borrowId);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int getTotalDueBooksCount() {
        String sql = "SELECT COUNT(*) as total FROM borrow_records " +
                     "WHERE status != 'returned' OR return_date IS NULL";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            if (rs.next()) {
                return rs.getInt("total");
            }
            
        } catch (SQLException e) {
            System.out.println("Error getting total due books count: " + e.getMessage());
            e.printStackTrace();
        }
        
        return 0;
    }
    
    public List<Map<String, Object>> getBorrowRecordsByDateRange(java.time.LocalDate startDate) {
    List<Map<String, Object>> records = new ArrayList<>();
    
    String sql = "SELECT br.borrow_id, br.member_id, br.book_id, " +
                 "br.issue_date, br.due_date, br.return_date, br.status, " +
                 "m.name as member_name, b.title as book_title " +
                 "FROM borrow_records br " +
                 "JOIN members m ON br.member_id = m.member_id " +
                 "JOIN books b ON br.book_id = b.book_id " +
                 "WHERE br.issue_date >= ? " +
                 "ORDER BY br.issue_date DESC";
    
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setDate(1, Date.valueOf(startDate));
        
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> record = new HashMap<>();
                record.put("borrow_id", rs.getInt("borrow_id"));
                record.put("member_id", rs.getInt("member_id"));
                record.put("book_id", rs.getInt("book_id"));
                record.put("issue_date", rs.getDate("issue_date"));
                record.put("due_date", rs.getDate("due_date"));
                record.put("return_date", rs.getDate("return_date"));
                record.put("status", rs.getString("status"));
                record.put("member_name", rs.getString("member_name"));
                record.put("book_title", rs.getString("book_title"));
                records.add(record);
            }
        }
        
    } catch (SQLException e) {
        System.out.println("Error getting filtered borrow records: " + e.getMessage());
        e.printStackTrace();
    }
    
    return records;
}
}