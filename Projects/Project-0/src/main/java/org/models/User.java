package org.models;

import org.enums.Role;
import org.enums.Status;

import java.sql.Date;
import java.util.List;

public class User {
    private int user_id;
    private String name;
    private String email;
    private String mobile;
    private String password;
    private Date DOJ;
    private Role role;
    private String description;
    private Status status;
    private List<Project> projects;
    private int manager;

    public User(int user_id, String name, String email, String mobile, String password, Date DOJ, Role role, String description, Status status, List<Project> projects, int manager) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.DOJ = DOJ;
        this.role = role;
        this.description = description;
        this.status = status;
        this.projects = projects;
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", DOJ=" + DOJ +
                ", role=" + role +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", projects=" + projects +
                ", manager=" + manager +
                '}';
    }

    public User(){}

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDOJ() {
        return DOJ;
    }

    public void setDOJ(Date DOJ) {
        this.DOJ = DOJ;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public int getManager() {
        return manager;
    }

    public void setManager(int manager) {
        this.manager = manager;
    }
}
