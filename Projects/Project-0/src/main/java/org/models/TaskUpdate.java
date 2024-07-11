package org.models;

import java.sql.Timestamp;

public class TaskUpdate {
    private int update_id;
    private Milestone milestone;
    private Task task;
    private  User user;

    private Timestamp timestamp;

    private String comments;

    public TaskUpdate(int update_id, Milestone milestone, Task task, User user, Timestamp timestamp, String comments) {
        this.update_id = update_id;
        this.milestone = milestone;
        this.task = task;
        this.user = user;
        this.timestamp = timestamp;
        this.comments = comments;
    }

    public TaskUpdate(){}

    public int getUpdate_id() {
        return update_id;
    }

    public void setUpdate_id(int update_id) {
        this.update_id = update_id;
    }

    public Milestone getMilestone() {
        return milestone;
    }

    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
