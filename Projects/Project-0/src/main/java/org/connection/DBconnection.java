package org.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    public  Connection connection;

    public static Logger logger=LoggerFactory.getLogger(DBconnection.class);

    private static DBconnection db;

    private final String url="jdbc:mysql://localhost:3306/revtaskmanagement";
    private final String user="root";
    private final String password="1234";

    private DBconnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        }catch(ClassNotFoundException e) {

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static DBconnection getDBConnection(){
        if(db==null) {
            db=new DBconnection();
            return db;
        }
        else {
            return db;
        }
    }
}
