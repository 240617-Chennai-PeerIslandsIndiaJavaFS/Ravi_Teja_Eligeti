package org.dao;

import org.connection.DBconnection;
import org.models.Milestone;
import org.models.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDaoImplementation implements  TaskDao{
    Connection con;
    ProjectDao pdao;

    MilestoneDAO mdao;

    UserDao udao;
    public static Logger logger= LoggerFactory.getLogger(TaskDaoImplementation.class);


    public TaskDaoImplementation(){
        con= DBconnection.getDBConnection().connection;
        mdao=new MileStoneDaoImplementation();
        udao=new UserDaoImplementation();
        pdao=new ProjectDaoImplementation();
    }
    @Override
    public Task createTask(Task task) {
        String query="insert into task(project_id,task_name,task_description,start_date,end_date,milestone_id,assignee,status) values(?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement ps=con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1,task.getProject().getProject_id());
            ps.setString(2,task.getTask_name());
            ps.setString(3,task.getDescription());
            ps.setDate(4,task.getStart_date());
            ps.setDate(5,task.getEnd_date());
            ps.setInt(6,1);
            ps.setInt(7,task.getUser().getUser_id());
            ps.setString(8,task.getStatus());
            int affectedRows = ps.executeUpdate();
            if(affectedRows>0){
                ResultSet rs=ps.getGeneratedKeys();
                if(rs.next()){
                    int id=rs.getInt(1);
                    task.setTask_id(id);
//                    System.out.println("id is: "+id);
                }
            }
            logger.info("Task has been created with name: "+task.getTask_name());

            return task;
        }
        catch (SQLException ex){
            logger.error("Error in creating the task");
            ex.printStackTrace();
        }
        return null;
    }





    @Override
    public List<Task> getAllProjectTasks(int project_id) {
        String query="select * from task where project_id=?";
        List<Task> tasks=new ArrayList<>();
        try {
            PreparedStatement ps=con.prepareStatement(query);
            ps.setInt(1,project_id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                tasks.add(getTaskById(rs.getInt(1)));
            }
            logger.info("Success in getting a project tasks");
            return tasks;
        }
        catch (SQLException e) {
            logger.error("Error in getting a project tasks");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Task getTaskById(int task_id) {
        String query="select * from task where task_id=?";
        try {
            PreparedStatement ps=con.prepareStatement(query);
            ps.setInt(1,task_id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Task task=new Task();
                task.setTask_id(rs.getInt(1));
                task.setProject(pdao.getProjectById(rs.getInt(2)));
                task.setTask_name(rs.getString(3));
                task.setDescription(rs.getString(4));
                task.setStart_date(rs.getDate(5));
                task.setEnd_date(rs.getDate(6));
                task.setMilestone(mdao.getMilestoneById(rs.getInt(8)));
                task.setUser(udao.getUserById(rs.getInt(9)));
                task.setStatus(rs.getString(10));
                logger.info("Success in getting task by id");
                return task;
            }
            return null;
        }
        catch (SQLException ex){
            logger.error("Error in fetching task by id");
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Task> userTasks(int project_id, int user_id) {
        String query="select * from task where project_id=? and assignee=?";
        try{
            PreparedStatement ps=con.prepareStatement(query);
            ps.setInt(1,project_id);
            ps.setInt(2,user_id);
            ResultSet rs= ps.executeQuery();
            List<Task> tasks=new ArrayList<>();
            while (rs.next()){
                Task task=new Task();
                task.setTask_id(rs.getInt(1));
                task.setProject(pdao.getProjectById(rs.getInt(2)));
                task.setTask_name(rs.getString(3));
                task.setDescription(rs.getString(4));
                task.setStart_date(rs.getDate(5));
                task.setEnd_date(rs.getDate(6));
                task.setMilestone(mdao.getMilestoneById(rs.getInt(8)));
                task.setUser(udao.getUserById(rs.getInt(9)));
                task.setStatus(rs.getString(10));
                tasks.add(task);
            }
            logger.info("Success in getting user tasks");
            return tasks;
        }
        catch (SQLException ex){
            logger.error("Error in getting user tasks");
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateTask(Task task) {
        String query="update task set milestone_id=?,assignee=?,status=?";
        try{
         PreparedStatement ps=con.prepareStatement(query);
         ps.setInt(1,task.getMilestone().getMilestone_id());
         ps.setInt(2,task.getUser().getUser_id());
         ps.setString(3,task.getStatus());
         int num= ps.executeUpdate();
         logger.info("Updating task successfull");
         return 1;
        }
        catch (SQLException ex){
            logger.error("Error in updating task");
            ex.printStackTrace();
        }
        return 0;
    }
}
