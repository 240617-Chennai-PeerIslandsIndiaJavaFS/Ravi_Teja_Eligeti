package org.dao;

import org.connection.DBconnection;
import org.models.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImplementation implements ClientDao{
    Connection con;

    public static Logger logger= LoggerFactory.getLogger(ClientDaoImplementation.class);

    public ClientDaoImplementation(){
        con= DBconnection.getDBConnection().connection;
    }
    @Override
    public boolean addClient(Client client) {
        String query="insert into client(company_name,point_of_contact,contact_email,contact_number,city,address) values(?,?,?,?,?,?)";
        try{
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,client.getCompany_name());
            ps.setString(2,client.getPoint_of_contact());
            ps.setString(3,client.getContact_email());
            ps.setString(4,client.getContact_number());
            ps.setString(5,client.getCity());
            ps.setString(6,client.getAddress());
            boolean b=ps.execute();
            logger.info("Client registration successfull");
            return true;
        }
        catch (Exception e){
            logger.error("Error in inserting client");
        }
        return false;
    }

    @Override
    public Client getClientById(int id) {
        Client client=new Client();
        String query="select * from client where client_id=?";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                client.setClient_id(rs.getInt(1));
                client.setCompany_name(rs.getString(2));
                client.setPoint_of_contact(rs.getString(3));
                client.setContact_email(rs.getString(4));
                client.setContact_number(rs.getString(5));
                client.setCity(rs.getString(6));
                client.setAddress(rs.getString(7));
            }
            logger.info("Client fetched successfully");


            return client;
        }
        catch (SQLException ex){
            logger.error("Error in fetching client");
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Client> fetchAllClients() {
        String query="select * from client";
        List<Client> list=new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Client client=new Client();
                client.setClient_id(rs.getInt(1));
                client.setCompany_name(rs.getString(2));
                client.setPoint_of_contact(rs.getString(3));
                client.setContact_email(rs.getString(4));
                client.setContact_number(rs.getString(5));
                client.setCity(rs.getString(6));
                client.setAddress(rs.getString(7));
                list.add(client);
            }
            logger.info("Fetching clients successfull");

            return list;
        }
        catch (SQLException e){
            logger.error("Error in fetching clients");
        }

        return null;
    }

    @Override
    public int updateClient(Client client) {
        String query="update client set point_of_contact=?,contact_email=?,contact_number=? where client_id=?";
        try {
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,client.getPoint_of_contact());
            ps.setString(2,client.getContact_email());
            ps.setString(3,client.getContact_number());
            ps.setInt(4,client.getClient_id());
            int num=ps.executeUpdate();
            logger.info("Update client successfull");
            return num;
        } catch (SQLException e) {
            logger.info("Error in updating client");
            e.printStackTrace();
        }
        return 0;
    }
}
