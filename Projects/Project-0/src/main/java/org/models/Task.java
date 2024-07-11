package org.models;

import java.sql.Date;

public class Task {
    private int task_id;

    private Project project;

    private Date start_date;

    private Date end_date;

    private String task_name;

    private String description;

    private  User manager;

    private Milestone milestone;

    private String status;

    public Task(int task_id, Project project, Date start_date, Date end_date, String task_name, String description, User user, Milestone milestone,String status) {
        this.task_id = task_id;
        this.project = project;
        this.start_date = start_date;
        this.end_date = end_date;
        this.task_name = task_name;
        this.description = description;
        this.manager = user;
        this.milestone = milestone;
        this.status=status;
    }

    public Task(){}

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return manager;
    }

    public void setUser(User user) {
        this.manager = user;
    }

    public Milestone getMilestone() {
        return milestone;
    }

    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
    }


}
