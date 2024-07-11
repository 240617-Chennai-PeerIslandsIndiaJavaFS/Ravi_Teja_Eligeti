package org.service;

import org.controller.Console;
import org.controller.UserController;
import org.dao.UserDaoImplementation;
import org.enums.Role;
import org.enums.Status;
import org.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    public UserDaoImplementation udao;
    public UserController controller;

    public Console console;
    public UserService(UserDaoImplementation udao){
        this.udao=udao;
    }
    public UserService(){
        controller =new UserController();
        udao=new UserDaoImplementation();
        console=new Console();

    }
    public User userLogin(String email,String password){
        User user=udao.loginUser(email);
        return user;

    }
    public boolean addUser(User user){
        User userDb=udao.loginUser(user.getEmail());
        if(userDb==null){
            boolean b=udao.addUser(user);
            return b;
        }
        else{
            return false;
        }
    }
    public List<User> getAllManagers(){
        List<User> users=new ArrayList<>();
        udao.getAllUsers().forEach(user ->{
            if(user.getRole()==Role.MANAGER){
                users.add(user);
            }
        });
        return users;
    }
    public List<User> getAllUsers(){
        return  udao.getAllUsers();
    }
    public User updateUser(User user){
        int num=udao.updateUser(user);
        if(num!=0){
            System.out.println("🟢✅Update Successfully ✅🟢");
        }
        else{
            System.out.println("❌❌Some thing went wrong!!!❌❌");

        }
        return user;
    }

    public User getUserById(int id){
        return udao.getUserById(id);
    }
     public User deleteUser(){
        return null;
     }
}
