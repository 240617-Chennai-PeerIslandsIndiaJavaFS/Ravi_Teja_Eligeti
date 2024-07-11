package org.dao;

import org.connection.DBconnection;
import org.enums.MessageStatus;
import org.models.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDaoImplementation implements  MessageDao{
    public static Logger logger=LoggerFactory.getLogger(MessageDaoImplementation.class);
    Connection connection;
    UserDao udao;

    public  MessageDaoImplementation(){
        connection= DBconnection.getDBConnection().connection;
        udao=new UserDaoImplementation();
    }
    @Override
    public int sendMessage(Message message) {
        String query="Insert into messages(sender_id,receiver_id,subject,content,status) values(?,?,?,?,?)";
        try{
            PreparedStatement ps=connection.prepareStatement(query);
            ps.setInt(1,message.getSender().getUser_id());
            ps.setInt(2,message.getReceiver().getUser_id());
            ps.setString(3,message.getSubject());
            ps.setString(4,message.getContent());
            ps.setString(5, String.valueOf(message.getStatus()));
            ps.executeUpdate();
            logger.info("Message sent successfully");
            return 1;
        }
        catch (SQLException ex){
            logger.error("Error in sending message");
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Message> getMyMessages(int id) {
        List<Message> myMessages=new ArrayList<>();
        String query="select * from messages where receiver_id=?";
        try{
            PreparedStatement ps=connection.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                Message message=new Message();
                message.setMessage_id(rs.getInt(1));
                message.setSender(udao.getUserById(rs.getInt(2)));
                message.setReceiver(udao.getUserById(rs.getInt(3)));
                message.setSubject(rs.getString(4));
                message.setContent(rs.getString(5 ));
                message.setStatus(MessageStatus.valueOf(rs.getString(6)));
                myMessages.add(message);
                logger.info("Fetching messages ");
            }
            return myMessages;
        }
        catch (SQLException ex){
            logger.info("Error in fetching messages");
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateStatus(int id) {
//        System.out.println("MEssage id"+id);
        String query="update messages set status=? where message_id=?";
        try {
            PreparedStatement ps=connection.prepareStatement(query);
            ps.setString(1,"SEEN");
            ps.setInt(2,id);
            ps.executeUpdate();
            logger.info("Message is set to seen");
            return 1;
        }
        catch (SQLException ex){
            logger.info("Error in setting message to seen");
            ex.printStackTrace();
        }
        return 0;
    }
}
