package org.dao;

import org.connection.DBconnection;
import org.models.Milestone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MileStoneDaoImplementation implements MilestoneDAO{
    public static Logger logger= LoggerFactory.getLogger(MileStoneDaoImplementation.class);

    Connection connection;

    public MileStoneDaoImplementation(){
        connection= DBconnection.getDBConnection().connection;
    }
    @Override
    public Milestone getMilestoneById(int id) {
        String query="select * from Milestone where milestone_id=?";
        try{
            PreparedStatement ps=connection.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                Milestone milestone=new Milestone();
                milestone.setMilestone_id(rs.getInt(1));
                milestone.setMilestone_name(rs.getString(2));
                milestone.setMilestone_description(rs.getString(3));
                logger.info("fetched milestone successfull");
                return milestone;
            }
            else{
                return null;
            }

        }
        catch (SQLException ex){
            logger.error("Error in fetching milestone");
            ex.printStackTrace();
        }
        return null;
    }
}
