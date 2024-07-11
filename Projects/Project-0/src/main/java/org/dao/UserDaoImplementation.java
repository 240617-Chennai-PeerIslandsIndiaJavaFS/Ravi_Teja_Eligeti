package org.dao;

import org.connection.DBconnection;
import org.enums.Role;
import org.enums.Status;
import org.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.SimpleTimeZone;

public class UserDaoImplementation implements UserDao{
    public static Logger logger= LoggerFactory.getLogger(UserDaoImplementation.class);
    public static Connection connection;
    public UserDaoImplementation(){
        connection=DBconnection.getDBConnection().connection;
    }


    @Override
    public User loginUser(String email) {
        User user=new User();
        String query="select * from user where email=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,email);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                user= getUserById(rs.getInt(1));
                logger.info("User fetched using email");
                return user;
            }
            else {
                return null;
            }
        }
        catch (SQLException e){
            logger.error("Error in login");
        }
        return null;
    }

    @Override
    public boolean addUser(User user) {
        String query="insert into user(name,email,mobile,password,role,status,description) values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,user.getName());
            ps.setString(2,user.getEmail());
            ps.setString(3,user.getMobile());
            ps.setString(4,user.getPassword());
            ps.setString(5, String.valueOf(user.getRole()));
            ps.setString(6,"ONLINE");
            ps.setString(7,user.getDescription());
            Boolean b= ps.execute();
            logger.info("Registration successfull");
            return true;
        }
        catch (SQLException e){
            logger.error("Error in registration user");
        }

        return false;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users=new ArrayList<>();
        String query="select * from user";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                User user=new User();
                user.setUser_id(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setMobile(rs.getString(4));
                user.setPassword(rs.getString(5));
                user.setDOJ(rs.getDate(6));
                user.setRole(Role.valueOf(rs.getString(7)));
                user.setDescription(rs.getString(8));
                user.setStatus(Status.valueOf(rs.getString(9)));
                user.setManager(rs.getInt(10));
                users.add(user);
            }
            logger.info("Fetched all users");
            return users;
        }
        catch (SQLException e){
            logger.error("Error in fetching users");

        }
        return null;
    }

    @Override
    public User getUserById(int id) {
        User user=new User();
        String query="select * from user where user_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                user.setUser_id(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setMobile(rs.getString(4));
                user.setPassword(rs.getString(5));
                user.setDOJ(rs.getDate(6));
                user.setRole(Role.valueOf(rs.getString(7)));
                user.setDescription(rs.getString(8));
                user.setStatus(Status.valueOf(rs.getString(9)));
                user.setManager(rs.getInt(10));
                logger.info("Fetched user with name "+user.getName());
                return  user;
            }
            else {
                System.out.println("doesnot have an element");
                return null;
            }
        }
        catch (SQLException e){
            logger.error("Error in fetching error");
        }
        return null;
    }

    @Override
    public int updateUser(User user) {
        String query="update user set email=?,mobile=?,password=?,DOJ=?,role=?,description=?,manager_id=?,status=? where user_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,user.getEmail());
            ps.setString(2,user.getMobile());
            ps.setString(3,user.getPassword());
            ps.setDate(4,user.getDOJ());
            ps.setString(5,String.valueOf(user.getRole()));
            ps.setString(6,user.getDescription());
            if(user.getManager()==0) {
                ps.setNull(7, Types.INTEGER);
            }
            else{
                ps.setInt(7,user.getManager());
            }
            ps.setString(8, String.valueOf(user.getStatus()));
            ps.setInt(9,user.getUser_id());
            int num=ps.executeUpdate();
            logger.info("Updating user is successfull");
            return num;
        }
        catch (SQLException exception){
            logger.error("Error in updating user");
            exception.printStackTrace();

        }
        return 0;
    }

    @Override
    public User deleteUser(int id) {
        String query="delete from user where user_id=?";
        try{
            PreparedStatement ps=connection.prepareStatement(query);
            ps.setInt(1,id);
            ps.executeUpdate();
            User user=getUserById(id);
            return user;
        }
        catch (SQLException exception){
            exception.printStackTrace();
        }
        return null;
    }


}
