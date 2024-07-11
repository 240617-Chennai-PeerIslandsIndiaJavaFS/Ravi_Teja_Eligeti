package org.dao;

import org.connection.DBconnection;
import org.models.TaskUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskUpdateDaoImplementation implements TaskUpdateDao{
    Connection con;
    MilestoneDAO mdao;

    TaskDao tdao;

    UserDao udao;

    public static Logger logger= LoggerFactory.getLogger(TaskUpdateDaoImplementation.class);

    public TaskUpdateDaoImplementation(){
        con= DBconnection.getDBConnection().connection;
        mdao=new MileStoneDaoImplementation();
        tdao=new TaskDaoImplementation();
        udao=new UserDaoImplementation();
    }
    @Override
    public TaskUpdate addTaskUpdate(TaskUpdate taskUpdate) {
        String query="insert into taskupdate(milestone_id,task_id,user_id,update_time,comment) values(?,?,?,?,?)";
        try{
            PreparedStatement ps=con.prepareStatement(query);
            ps.setInt(1,taskUpdate.getMilestone().getMilestone_id());
            ps.setInt(2,taskUpdate.getTask().getTask_id());
            ps.setInt(3,taskUpdate.getUser().getUser_id());
            ps.setTimestamp(4,taskUpdate.getTimestamp());
            ps.setString(5,taskUpdate.getComments());
            ps.execute();
            logger.info("Milestone has been created");
            return taskUpdate;
        }
        catch (SQLException ex){
            logger.error("Error in creating milestone");
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TaskUpdate> taskupdatesOfaTask(int task_id) {
        String query="select * from taskupdate where task_id=?";
        List<TaskUpdate> taskUpdates=new ArrayList<>();
        try{
            PreparedStatement ps=con.prepareStatement(query);
            ps.setInt(1,task_id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                TaskUpdate taskUpdate=new TaskUpdate();
                taskUpdate.setUpdate_id(rs.getInt(1));
                taskUpdate.setMilestone(mdao.getMilestoneById(rs.getInt(2)));
                taskUpdate.setUser(udao.getUserById(rs.getInt(4)));
                taskUpdate.setTask(tdao.getTaskById(3));
                taskUpdate.setTimestamp(rs.getTimestamp(5));
                taskUpdate.setComments(rs.getString(6));
                taskUpdates.add(taskUpdate);
            }
            logger.info("Milestones of a task are viewing");
            return taskUpdates;
        }
        catch (SQLException ex){
            logger.error("Error in fetching milestones");
            ex.printStackTrace();
        }
        return null;
    }
}
