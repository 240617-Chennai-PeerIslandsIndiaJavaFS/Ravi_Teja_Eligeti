package org.controller;

import org.models.User;
import org.service.UserService;

import java.util.List;

public class UserController {
    public static User user;
    public UserService userService;
        public User login(String email,String password){
            userService=new UserService();
            user=userService.userLogin(email,password);
            return user;
        }
    public boolean addUser(User user){
        userService=new UserService();
        return userService.addUser(user);
    }

    public List<User> getAllManagers(){
            userService=new UserService();
            return userService.getAllManagers();
    }

    public List<User> getAllUsers(){
            userService=new UserService();
            return userService.getAllUsers();
    }

    public User updateUser(User user){
            userService=new UserService();
            return userService.updateUser(user);
    }

    public User getUserById(int id){
            return new UserService().getUserById(id);
    }
}
