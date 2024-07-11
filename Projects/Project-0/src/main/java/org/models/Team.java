package org.models;

import java.util.List;

public class Team {
    private int team_id;
    private String team_name;

    private Project project;

    private User Manager;

    private List<User> team_members;

    public Team(int team_id, String team_name, Project project, User manager, List<User> team_members) {
        this.team_id = team_id;
        this.team_name = team_name;
        this.project = project;
        Manager = manager;
        this.team_members = team_members;
    }

    public Team(){}

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getManager() {
        return Manager;
    }

    public void setManager(User manager) {
        Manager = manager;
    }

    public List<User> getTeam_members() {
        return team_members;
    }

    public void setTeam_members(List<User> team_members) {
        this.team_members = team_members;
    }
}
