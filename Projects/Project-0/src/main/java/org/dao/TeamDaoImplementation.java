package org.dao;

import org.connection.DBconnection;
import org.models.Team;
import org.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TeamDaoImplementation implements TeamDao{
    public static Logger logger= LoggerFactory.getLogger(TeamDaoImplementation.class);
    Connection connection;
    ProjectDao pdao;

    UserDao udao;

    public TeamDaoImplementation(){
        connection= DBconnection.getDBConnection().connection;
        pdao=new ProjectDaoImplementation();
        udao=new UserDaoImplementation();
    }


    @Override
    public boolean createTeam(Team team) {
        String query="insert into team(project_id,manager_id,team_name) values(?,?,?)";
        try {
            PreparedStatement ps=connection.prepareStatement(query);
            ps.setInt(1,team.getProject().getProject_id());
            ps.setInt(2,team.getManager().getUser_id());
            ps.setString(3,team.getTeam_name());
            boolean b=ps.execute();
            logger.info("Team has been created with name "+team.getTeam_name());
            return true;
        }
        catch (SQLException e){
            logger.error("Error in creating the team");

        }
        return false;
    }

    @Override
    public int updateTeam(User user) {
        String query="delete from team_details where team_member=?";
        try {
            PreparedStatement ps=connection.prepareStatement(query);
            ps.setInt(1,user.getUser_id());
            int num=ps.executeUpdate();
            logger.info("Team has been updated");
            return num;
        } catch (SQLException e) {
            logger.error("Error in updating team");
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean addTeamMember(int team_id, User user) {
        String query="insert into team_details(team_id,team_member) values(?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,team_id);
            ps.setInt(2,user.getUser_id());
            int num=ps.executeUpdate();
            logger.info("Succesfull added member into team");
            return true;
        }
        catch (SQLException ex){
            logger.error("Error in adding member into team");
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> getTeamMembers(int team_id) {
        String query="select * from team_details where team_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,team_id);
            ResultSet rs=ps.executeQuery();
            List<User> users=new ArrayList<>();
            if(rs.next()){
                User user=udao.getUserById(rs.getInt(2));
                users.add(user);
                while (rs.next()){
                    users.add(udao.getUserById(rs.getInt(2)));
                }
                logger.info("Success in getting users of a team");
                return users;
            }
            else {
                logger.error("Error in fetching team members");
                return null;
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Team getTeamByProject(int id) {
        String query="select * from team where project_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Team team=new Team();
                team.setTeam_id(rs.getInt(1));
                team.setProject(pdao.getProjectById(rs.getInt(2)));
                team.setManager(udao.getUserById(rs.getInt(3)));
                team.setTeam_name(rs.getString(4));
                logger.info("Success in fetching a team");
                return  team;
            }
        }
        catch (SQLException ex){
            logger.error("error in fetching team");
            ex.printStackTrace();
        }
        return null;
    }




}
