package TestingFiles;

import org.dao.TaskUpdateDaoImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.models.TaskUpdate;
import org.service.TaskUpdateService;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TaskUpdateTest {
    public static TaskUpdateDaoImplementation mockDao;

    public static List<TaskUpdate> taskUpdates;

    public static TaskUpdateService mockService;

    public static TaskUpdate taskUpdate;
    @BeforeAll
    public static void mockTestUpdate(){
        mockDao=mock();
        taskUpdate= Mockito.mock(TaskUpdate.class);
        mockService=new TaskUpdateService(mockDao);


    }

    @Test
    public void createTaskUpdateTest(){
        when(mockService.createTaskUpdate(taskUpdate)).thenReturn(taskUpdate);
        Assertions.assertEquals(taskUpdate,mockService.createTaskUpdate(taskUpdate));
    }

    @Test
    public void fetchTaskupdatesBytask(){
        when(mockService.getTaskUpdateByTaskId(1)).thenReturn(taskUpdates);
        Assertions.assertIterableEquals(taskUpdates,mockService.getTaskUpdateByTaskId(1));
    }
}
