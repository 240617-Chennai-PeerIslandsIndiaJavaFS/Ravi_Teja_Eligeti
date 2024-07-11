package TestingFiles;

import org.dao.UserDaoImplementation;
import org.enums.Role;
import org.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.models.User;
import org.service.UserService;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserTest {
    public static UserDaoImplementation udao;

    public static UserDaoImplementation actualDao;

    public static UserService userService;

    public static UserService actualService;

    public static User sampleUser;

    @BeforeAll
    public static void createObject(){
        sampleUser=new User(1,"test","test@gmail.com","+91 8376549876","test@123", java.sql.Date.valueOf(LocalDate.now()), Role.MANAGER,"test", Status.ONLINE,null,2);
        actualDao=new UserDaoImplementation();
        actualService=new UserService();
        udao=mock();
        userService=new UserService(udao);
    }

    @Test
    public void testLogin(){
        User user1=new User(1,"test","test@gmail.com","+91 8376549876","test@123", java.sql.Date.valueOf(LocalDate.now()), Role.MANAGER,"test", Status.ONLINE,null,2);
        User user2=new User(1,"test2","test2@gmail.com","+91 8376349876","test2@123", java.sql.Date.valueOf(LocalDate.now()), Role.MANAGER,"test2", Status.ONLINE,null,1);
        when(userService.userLogin("test@gmail.com","test@123")).thenReturn(user1);
        when(userService.userLogin("test2@gmail.com","test2@123")).thenReturn(user2);
        when(userService.userLogin("new@gmail.com","new")).thenReturn(null);
        User returnedUser1=userService.userLogin("test@gmail.com","test@123");
        User ruser2=userService.userLogin("test2@gmail.com","test2@123");
        User ruser3=userService.userLogin("new@gmail.com","new");

        Assertions.assertEquals(user1,returnedUser1);
        Assertions.assertEquals(user2,ruser2);
        Assertions.assertEquals(null,ruser3);
    }
//    Test without mokito

    @Test
    public void userRegisterSuccess(){
        Assertions.assertEquals(true,actualService.addUser(sampleUser));
    }

    @Test
    public void wrongRegistration(){
        Assertions.assertNotEquals(true,actualService.addUser(sampleUser));
    }

    @Test
    public void testGetAllUsers(){
        List<User> users=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String name = "User " + (i + 1);
            String email = "user" + (i + 1) + "@example.com";
            // You can customize these further for more variety
            Role role = Role.MANAGER; // Assuming a default role
            Status status = Status.ONLINE; // Assuming a default status
            Date DOJ = new Date(System.currentTimeMillis()); // Today's date

            User user = new User(0, name, email, "", "", DOJ, role, "", status, null, 0);
            users.add(user);
        }

        when(userService.getAllUsers()).thenReturn(users);

        Assertions.assertIterableEquals(users,userService.getAllUsers());

    }



}
