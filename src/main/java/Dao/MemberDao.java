package DAO;

import Database.DBConnection;
import Model.Member;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {

    // Add member
    public boolean addMember(Member member) {
        String sql = "INSERT INTO members (name, email, phone, address, joined_date) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, member.getName());
            pst.setString(2, member.getEmail());
            pst.setString(3, member.getPhone());
            pst.setString(4, member.getAddress());
            pst.setDate(5, member.getJoinedDate());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Update member
    public boolean updateMember(Member member) {
        String sql = "UPDATE members SET name=?, email=?, phone=?, address=?, joined_date=? WHERE member_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, member.getName());
            pst.setString(2, member.getEmail());
            pst.setString(3, member.getPhone());
            pst.setString(4, member.getAddress());
            pst.setDate(5, member.getJoinedDate());
            pst.setInt(6, member.getMemberId());

            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete member
    public boolean deleteMember(int memberId) {
        String sql = "DELETE FROM members WHERE member_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, memberId);
            return pst.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get all member
    public List<Member> getAllMembers() {
        List<Member> memberList = new ArrayList<>();
        String sql = "SELECT * FROM members";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getInt("member_id"));
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                member.setPhone(rs.getString("phone"));
                member.setAddress(rs.getString("address"));
                member.setJoinedDate(rs.getDate("joined_date"));
                memberList.add(member);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return memberList;
    }

    // Get member by name
    public Member getMemberByName(String name) {
        Member member = null;
        String sql = "SELECT * FROM members WHERE name=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                member = new Member();
                member.setMemberId(rs.getInt("member_id"));
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                member.setPhone(rs.getString("phone"));
                member.setAddress(rs.getString("address"));
                member.setJoinedDate(rs.getDate("joined_date"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return member;
    }

    // Get member ID
    public Member getMemberById(int memberId) {
        Member member = null;
        String sql = "SELECT * FROM members WHERE member_id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, memberId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                member = new Member();
                member.setMemberId(rs.getInt("member_id"));
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                member.setPhone(rs.getString("phone"));
                member.setAddress(rs.getString("address"));
                member.setJoinedDate(rs.getDate("joined_date"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return member;
    }

    // search 
    public List<Member> searchMembers(String searchTerm) {
        List<Member> memberList = new ArrayList<>();
        String sql = "SELECT * FROM members WHERE name LIKE ? OR email LIKE ? OR phone LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            String searchPattern = "%" + searchTerm + "%";
            pst.setString(1, searchPattern);
            pst.setString(2, searchPattern);
            pst.setString(3, searchPattern);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getInt("member_id"));
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                member.setPhone(rs.getString("phone"));
                member.setAddress(rs.getString("address"));
                member.setJoinedDate(rs.getDate("joined_date"));
                memberList.add(member);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return memberList;
    }
    
    
     public int getTotalMembersCount() {
        String sql = "SELECT COUNT(*) as total FROM members";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            if (rs.next()) {
                return rs.getInt("total");
            }
            
        } catch (SQLException e) {
            System.out.println("Error getting total members count: " + e.getMessage());
            e.printStackTrace();
        }
        
        return 0;
    }
}