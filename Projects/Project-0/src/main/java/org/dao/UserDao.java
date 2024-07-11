package org.dao;
import java.util.*;
import org.models.User;

public interface UserDao {

    public User loginUser(String email);

    public boolean addUser(User user);

    public List<User>  getAllUsers();

    public User getUserById(int id);

    public  int updateUser(User user);

    public User deleteUser(int id);



}
