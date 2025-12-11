/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Account;
import Connection.DBConnection;
import java.sql.*;
/**
 *
 * @author ASUS
 */
public class AccountDAO {
        public Account login(String username, String password) throws SQLException {
        String sql = "SELECT accountID, username, password, role "
                   + "FROM Accounts WHERE username = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);           // for thesis demo only – normally hash

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Account acc = new Account();
                    acc.setAccountID(rs.getInt("accountID"));
                    acc.setUsername(rs.getString("username"));
                    acc.setPassword(rs.getString("password"));
                    acc.setRole(rs.getString("role"));
                    return acc;
                }
            }
        }
        return null;   // invalid login
    }

    public void create(Account acc) throws SQLException {
        String sql = "INSERT INTO Accounts(username, password, role) VALUES (?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, acc.getUsername());
            ps.setString(2, acc.getPassword());
            ps.setString(3, acc.getRole());
            ps.executeUpdate();
        }
    }

    // update, delete, search methods can follow same pattern
    
}
