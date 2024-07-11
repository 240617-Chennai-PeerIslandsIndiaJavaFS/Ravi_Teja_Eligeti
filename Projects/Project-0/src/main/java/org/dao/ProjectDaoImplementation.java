package org.dao;

import org.connection.DBconnection;
import org.enums.ProjectStatus;
import org.models.Project;
import org.models.Team;
import org.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDaoImplementation implements ProjectDao{

    public static Logger logger=LoggerFactory.getLogger(ProjectDaoImplementation.class);
    Connection con;
    ClientDao cdao;
    UserDao udao;



    public ProjectDaoImplementation(){
        con= DBconnection.getDBConnection().connection;
        cdao=new ClientDaoImplementation();
        udao=new UserDaoImplementation();

    }
    @Override
    public Project addProject(Project project) {
        String query="insert into project(client_id,project_name,description,start_date,end_date,status,manager_id) values(?,?,?,?,?,?,?)";
        try{
            PreparedStatement ps=con.prepareStatement(query);
            ps.setInt(1,project.getClient().getClient_id());
            ps.setString(2,project.getProject_name());
            ps.setString(3,project.getDescription());
            ps.setDate(4,project.getStart_date());
            ps.setDate(5,project.getEnd_date());
            ps.setString(6,"PROGRESS");
            ps.setInt(7,project.getManager().getUser_id());
            int data=ps.executeUpdate();
            logger.info("Project has been addede "+project.getProject_name()+".");
            return project;
        }
        catch (SQLException e){
            logger.error("Error in adding the project");
        }
        return null;
    }

    public Project getProjectByName(String name){
        String query="select * from project where project_name=?";
        try{
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,name);
            ResultSet rs=ps.executeQuery();
            Project project=new Project();
            if(rs.next()){
                project.setProject_id(rs.getInt(1));
                project.setProject_name(rs.getString(2));
                logger.info("Project fetched successfully using name");
            }
            return project;
        }
        catch (SQLException ex){
            logger.error("Error in fetching project using name");
        }
        return null;
    }

    @Override
    public Project getProjectById(int id) {
        String query="select * from project where project_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Project project=new Project();
//                List<User> projectWorkers=new ArrayList<>();
                project.setProject_id(rs.getInt(1));
                project.setClient(cdao.getClientById(rs.getInt(2)));
                project.setProject_name(rs.getString(3));
                project.setDescription(rs.getString(4));
                project.setTeamMembers(getAllProjectMembers(rs.getInt(1)));
                project.setStart_date(rs.getDate(5));
                project.setEnd_date(rs.getDate(6));
                project.setStatus(ProjectStatus.valueOf(rs.getString(7)));
                project.setManager(udao.getUserById(rs.getInt(8)));
                logger.info("Fetched object is: "+project.getProject_name());
                return project;
            }
        }
        catch (SQLException ex){
            logger.info("Error in fetching project using id");
            ex.printStackTrace();
        }
return  null;
    }

    @Override
    public List<Project> fetchAllProjects() {
        List<Project> projects=new ArrayList<>();
        String query="select * from project";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Project project=new Project();
                project.setProject_id(rs.getInt(1));
                project.setClient(cdao.getClientById(rs.getInt(2)));
                project.setProject_name(rs.getString(3));
                project.setDescription(rs.getString(4));
                project.setStart_date(rs.getDate(5));
                project.setEnd_date(rs.getDate(6));
                project.setStatus(ProjectStatus.valueOf(rs.getString(7)));
                project.setManager(udao.getUserById(rs.getInt(8)));
                projects.add(project);
            }
            logger.info("Fetched all projects");
            return projects;
        }
        catch (SQLException ex){
            logger.error("Error in finding");
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addMemberIntoProject(int project, int member) {
        String query="insert into project_user(project_id,user_id) values(?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,project);
            ps.setInt(2,member);
            boolean b=ps.execute();
            logger.info("User has been added to project");
            return true;
        }
        catch (SQLException ex){
            logger.error("Error in adding user to project");
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> getAllProjectMembers(int id) {
        String query="select * from project_user where project_id=?";
        List<User> users=new ArrayList<>();
        try{
            PreparedStatement ps=con.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                users.add(udao.getUserById(rs.getInt(3)));
            }
            logger.info("Returned users");
            return users;
        }
        catch (SQLException ex){
            logger.error("Error in returing users");
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Project> getMemberProjects(int user_id) {
//        System.out.println(user_id+" in dao");
        String query="select * from project_user where user_id=?";
        try{
            PreparedStatement ps=con.prepareStatement(query);
            ps.setInt(1,user_id);
            ResultSet rs=ps.executeQuery();
            List<Project> projects=new ArrayList<>();
            while (rs.next()){
//                System.out.println(rs.getInt(2));
                Project p=getProjectById(rs.getInt(2));
//                System.out.println(p+"Project");
                projects.add(p);
            }
//            System.out.println(projects);
            logger.info("Fetched all projects of user");
            return projects;
        }
        catch (SQLException ex){
            logger.error("Error fetching projects");
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateProject(Project project) {
        String query="update project set end_date=?,status=?,manager_id=? where project_id=?";
        try{
            PreparedStatement ps=con.prepareStatement(query);
            ps.setDate(1,project.getEnd_date());
            ps.setString(2, String.valueOf(ProjectStatus.FINISHED));
            ps.setInt(3,project.getManager().getUser_id());
            ps.setInt(4,project.getProject_id());
            int num=ps.executeUpdate();
            logger.info("Update project successfull");
            return num;
        }
        catch (SQLException ex){
            logger.error("Error in updating project");
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteUserFromProject(int user_id,int project_id) {
        String query="delete from project_user where project_id=? and user_id=?";
        try {
            PreparedStatement ps=con.prepareStatement(query);
            ps.setInt(1,project_id);
            ps.setInt(2,user_id);
            int num=ps.executeUpdate();
            logger.info("Successfully removed user from project");
            return num;
        } catch (SQLException e) {
            logger.info("error in removing user from project");
            e.printStackTrace();
        }
        return 0;
    }


    public List<Project> fetchManagerProjects(int id){
        String query="select * from project where manager_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            List<Project> projects=new ArrayList<>();
            while (rs.next()){
                Project project=new Project();
                project.setProject_id(rs.getInt(1));
                project.setClient(cdao.getClientById(rs.getInt(2)));
                project.setProject_name(rs.getString(3));
                project.setDescription(rs.getString(4));
                project.setStart_date(rs.getDate(5));
                project.setEnd_date(rs.getDate(6));
                project.setStatus(ProjectStatus.valueOf(rs.getString(7)));
                project.setManager(udao.getUserById(rs.getInt(8)));
                projects.add(project);
            }
            logger.info("Getting projects of manager successfull");
            return projects;
        }
        catch (SQLException ex){
            logger.error("Error in feteching projects");
                ex.printStackTrace();
        }
        return null;
    }
}
