package TestingFiles;

import org.dao.ProjectDaoImplementation;
import org.enums.ProjectStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.models.Project;
import org.service.ProjectService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProjectTest {
    public static ProjectDaoImplementation mockData;

    public static ProjectService mockService;

    public static List<Project> projects=new ArrayList<>();
    @BeforeAll
    public static void createMock(){
        for (int i = 0; i < 10; i++) {
            String name = "Project " + (i + 1);
            String description = "Sample project description for project " + (i + 1);
            ProjectStatus status = ProjectStatus.PROGRESS; // Assuming a default status
            Date startDate = new Date(System.currentTimeMillis()); // Today's date
            Date endDate = new Date(startDate.getTime() + (long) (7 * 24 * 60 * 60 * 1000)); // One week from now

            Project project = new Project(0, null, name, description, startDate, endDate, status, null, null);
            projects.add(project);
        }
        mockData=mock();
        mockService=new ProjectService(mockData);
    }

    @Test
    public void addProject(){
            when(mockService.addProject(projects.get(1))).thenReturn(projects.get(1));
            Project output=mockService.addProject(projects.get(1));
        Assertions.assertEquals(projects.get(1),output);

    }

    @Test
    public void testGetProject(){
        when(mockService.getProjectById(1)).thenReturn(projects.get(1));
        when(mockService.getProjectById(2)).thenReturn(projects.get(2));

        Assertions.assertEquals(projects.get(1),mockService.getProjectById(1));
        Assertions.assertEquals(projects.get(2),mockService.getProjectById(2));
    }

    @Test
    public void testFetchAllProjects(){
        when(mockService.fetchAllProjects()).thenReturn(projects);
        Assertions.assertIterableEquals(projects,mockService.fetchAllProjects());
    }
}
