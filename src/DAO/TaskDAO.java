/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Connection.DBConnection;
import Model.Member;
import Model.Task;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ASUS
 */
public class TaskDAO {
    public void addTask(Task task) throws SQLException {
        String sql = "INSERT INTO Tasks(title, description, assignedTo, status, dueDate) "
                   + "VALUES (?,?,?,?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, task.getTitle());
            ps.setString(2, task.getDescription());

            if (task.getAssignedTo() != null) {
                ps.setInt(3, task.getAssignedTo().getMemberID());
            } else {
                ps.setNull(3, Types.INTEGER);
            }

            ps.setString(4, task.getStatus());

            if (task.getDueDate() != null) {
                ps.setDate(5, new java.sql.Date(task.getDueDate().getTime()));
            } else {
                ps.setNull(5, Types.DATE);
            }

            ps.executeUpdate();
        }
    }

    public void updateTask(Task task) throws SQLException {
        String sql = "UPDATE Tasks SET title=?, description=?, assignedTo=?, "
                   + "status=?, dueDate=? WHERE taskID=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, task.getTitle());
            ps.setString(2, task.getDescription());

            if (task.getAssignedTo() != null) {
                ps.setInt(3, task.getAssignedTo().getMemberID());
            } else {
                ps.setNull(3, Types.INTEGER);
            }

            ps.setString(4, task.getStatus());

            if (task.getDueDate() != null) {
                ps.setDate(5, new java.sql.Date(task.getDueDate().getTime()));
            } else {
                ps.setNull(5, Types.DATE);
            }

            ps.setInt(6, task.getTaskID());
            ps.executeUpdate();
        }
    }
    public void deleteTask(int taskID) throws SQLException {
        String sql = "DELETE FROM Tasks WHERE taskID=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, taskID);
            ps.executeUpdate();
        }
    }
    public List<Task> getAllTasks() throws SQLException {
        String sql = "SELECT t.taskID, t.title, t.description, t.status, t.dueDate, "
                   + "m.memberID, m.name, m.email, m.role "
                   + "FROM Tasks t "
                   + "LEFT JOIN Members m ON t.assignedTo = m.memberID";

        List<Task> list = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Task t = new Task();
                t.setTaskID(rs.getInt("taskID"));
                t.setTitle(rs.getString("title"));
                t.setDescription(rs.getString("description"));
                t.setStatus(rs.getString("status"));

                java.sql.Date due = rs.getDate("dueDate");
                if (due != null) {
                    t.setDueDate(new java.util.Date(due.getTime()));
                }

                int memberID = rs.getInt("memberID");
                if (!rs.wasNull()) {
                    Member m = new Member();
                    m.setMemberID(memberID);
                    m.setName(rs.getString("name"));
                    m.setEmail(rs.getString("email"));
                    m.setRole(rs.getString("role"));
                    t.setAssignedTo(m);
                }

                list.add(t);
            }
        }

        return list;
    }
    public void assignTask(int taskID, int memberID) throws SQLException {
        String sql = "UPDATE Tasks SET assignedTo = ?, status = ? WHERE taskID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, memberID);
            ps.setString(2, "ASSIGNED");
            ps.setInt(3, taskID);
            ps.executeUpdate();
        }
    }
    public void updateTaskStatus(int taskID, String newStatus) throws SQLException {
        String sql = "UPDATE Tasks SET status = ? WHERE taskID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newStatus);
            ps.setInt(2, taskID);
            ps.executeUpdate();
        }
    } 
}
