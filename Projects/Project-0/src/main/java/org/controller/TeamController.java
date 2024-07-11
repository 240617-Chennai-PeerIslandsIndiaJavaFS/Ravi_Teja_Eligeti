package org.controller;

import org.models.Project;
import org.models.Team;
import org.models.User;
import org.service.TeamService;

import java.util.List;

public class TeamController {
    public static Team team;
    TeamService service;
    public void createTeam(Team team){
        service=new TeamService();
        service.addTeam(team);
    }

    public Team getTeamByProject(int id){
        service=new TeamService();
        return service.getTeamByProject(id);
    }

    public List<User> getAllTeamMembers(int team_id){
            return  new TeamService().getAllMembersWorkinginTeam(team_id);
    }
    public boolean addTeamMembersToProject(Team team,User member,Project project){
        return new  TeamService().addMemberIntoTeam(team,member,project);
    }

    public int removeFromTeam(User user){
        return new TeamService().removeFromTeam(user);
    }

}
