/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Task {
    private int taskID;
    private String title;
    private String description;
    private Member assignedTo;
    private String status;
    private Date dueDate;

    // --------- Constructors ---------

    public Task() {
    }

    public Task(int taskID,
                String title,
                String description,
                Member assignedTo,
                String status,
                Date dueDate) {
        this.taskID = taskID;
        this.title = title;
        this.description = description;
        this.assignedTo = assignedTo;
        this.status = status;
        this.dueDate = dueDate;
    }

    // --------- Getters & Setters ---------

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Member getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Member assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    // --------- UML Methods ---------

    /**
     * Assigns this task to the given member and sets a default status
     * if none is currently set.
     */
    public void assignTask(Member member) {
        this.assignedTo = member;

        // if status not set yet, mark as ASSIGNED
        if (this.status == null || this.status.isEmpty()) {
            this.status = "ASSIGNED";
        }
    }

    /**
     * Updates the status of the task (e.g. PENDING → IN PROGRESS → DONE).
     */
    public void updateTaskStatus(String newStatus) {
        if (newStatus == null || newStatus.trim().isEmpty()) {
            throw new IllegalArgumentException("Task status cannot be empty.");
        }
        this.status = newStatus.trim();
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskID=" + taskID +
                ", title='" + title + '\'' +
                ", assignedTo=" + (assignedTo != null ? assignedTo.getName() : "none") +
                ", status='" + status + '\'' +
                ", dueDate=" + dueDate +
                '}';
    }
    
}
