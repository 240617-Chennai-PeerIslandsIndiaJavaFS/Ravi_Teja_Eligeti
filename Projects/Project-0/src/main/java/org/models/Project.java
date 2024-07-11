package org.models;

import org.enums.ProjectStatus;

import java.sql.Date;
import java.util.List;

public class Project {

    private int project_id;

    private Client client;

    private String project_name;

    private String description;

    private Date start_date;

    private Date end_date;

    private ProjectStatus status;

    private List<User> teamMembers;

    private User manager;

    public Project(int project_id, Client client, String project_name, String description, Date start_date, Date end_date, ProjectStatus status, List<User> teamMembers, User manager) {
        this.project_id = project_id;
        this.client = client;
        this.project_name = project_name;
        this.description = description;
        this.start_date = start_date;
        this.end_date = end_date;
        this.status = status;
        this.teamMembers = teamMembers;
        this.manager = manager;
    }

    public Project(){}

    public List<User> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(List<User> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }
}
