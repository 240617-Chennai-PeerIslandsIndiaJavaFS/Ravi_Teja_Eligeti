package TestingFiles;

import org.dao.TaskDao;
import org.dao.TaskDaoImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.models.Project;
import org.models.Task;
import org.models.User;
import org.service.TaskService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TaskTest {
    public static TaskDaoImplementation mockDao;

    public static TaskService mockservie;

    public static List<Task> tasks = new ArrayList<>();

    @BeforeAll
    public static void createMock(){
         tasks = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            String name = "Task " + (i + 1);
            String description = "Sample task description for task " + (i + 1);
            Date startDate = new Date(System.currentTimeMillis()); // Today's date
            Date endDate = new Date(startDate.getTime() + (long) (3 * 24 * 60 * 60 * 1000)); // Three days from now
            String status = "CREATED"; // Assuming a default status


            Project project = null; // Replace with actual Project object
            User manager = null; // Replace with actual User object

            Task task = new Task(0, project, startDate, endDate, name, description, manager, null, status);
            tasks.add(task);

        }
        mockDao=mock();
        mockservie=new TaskService(mockDao);
    }
    @Test
    public void testTestCreation(){
        when(mockservie.createTask(tasks.get(1))).thenReturn(tasks.get(1));
        Assertions.assertEquals(tasks.get(1),mockservie.createTask(tasks.get(1)));

    }



}
