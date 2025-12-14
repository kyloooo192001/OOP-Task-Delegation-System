/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Connection.DBConnection;
import Model.Member;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ASUS
 */
public class MemberDAO {
    public void addMember(Member m) throws SQLException {
        String sql = "INSERT INTO Members(name, email, role) VALUES (?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, m.getName());
            ps.setString(2, m.getEmail());
            ps.setString(3, m.getRole());
            ps.executeUpdate();
        }
    }

    public void updateMember(Member m) throws SQLException {
        String sql = "UPDATE Members SET name=?, email=?, role=? WHERE memberID=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, m.getName());
            ps.setString(2, m.getEmail());
            ps.setString(3, m.getRole());
            ps.setInt(4, m.getMemberID());
            ps.executeUpdate();
        }
    }

    public void deleteMember(int memberID) throws SQLException {
        String sql = "DELETE FROM Members WHERE memberID=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, memberID);
            ps.executeUpdate();
        }
    }

    public List<Member> getAllMembers() throws SQLException {
        String sql = "SELECT memberID, name, email, role FROM Members";
        List<Member> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Member m = new Member();
                m.setMemberID(rs.getInt("memberID"));
                m.setName(rs.getString("name"));
                m.setEmail(rs.getString("email"));
                m.setRole(rs.getString("role"));
                list.add(m);
            }
        }
        return list;
    }
    
}
