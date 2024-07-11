package org.service;

import org.controller.Console;
import org.controller.TeamController;
import org.dao.*;
import org.models.Project;
import org.models.Team;
import org.models.User;

import java.util.List;

public class TeamService {
    TeamDao teamDao;
    Console console;

    UserDao udao;

    ProjectDao pdao;

    public TeamService(TeamDaoImplementation teamDao,UserDaoImplementation udao,ProjectDaoImplementation pdao){
        this.teamDao=teamDao;
        this.udao=udao;
        this.pdao=pdao;
    }

    public TeamService(){
        teamDao=new TeamDaoImplementation();
        console=new Console();
        udao=new UserDaoImplementation();
        pdao=new ProjectDaoImplementation();
    }

    public void addTeam(Team team){
        boolean b=teamDao.createTeam(team);
        if(b) {
            System.out.println("✅🟢Team has been created🟢✅");
            console.adminOptions();

        }
        else {
            System.out.println("Something went wrong");
            console.adminOptions();
        }
    }

    public Team getTeamByProject(int id){
       Team team= teamDao.getTeamByProject(id);
        return team;
    }

    public List<User> getAllMembersWorkinginTeam(int team_id){
        return teamDao.getTeamMembers(team_id);
    }

    public boolean addMemberIntoTeam(Team team,User user,Project project){
        try {
            user.setManager(team.getManager().getUser_id());
            udao.updateUser(user);
            pdao.addMemberIntoProject(project.getProject_id(), user.getUser_id());
            teamDao.addTeamMember(team.getTeam_id(), user);
            return true;
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    public int removeFromTeam(User user){
        return  teamDao.updateTeam(user);
    }
}
