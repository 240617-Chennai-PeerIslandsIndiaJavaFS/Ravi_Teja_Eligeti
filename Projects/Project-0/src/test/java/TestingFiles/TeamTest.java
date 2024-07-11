package TestingFiles;

import org.dao.ProjectDaoImplementation;
import org.dao.TeamDaoImplementation;
import org.dao.UserDaoImplementation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.models.Team;
import org.models.User;
import org.service.TeamService;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TeamTest {
    public static TeamDaoImplementation tdao;

    public static ProjectDaoImplementation pdao;

    public static UserDaoImplementation udao;

    public static TeamService teamService;

    public static Team team;

    public static User user;

    public static List<Team> teams;

    @BeforeAll
    public static void  mockTeam(){
        tdao=mock();
        pdao= mock();
        udao=Mockito.mock();
        team=Mockito.mock(Team.class);
        user=Mockito.mock(User.class);
        teamService=new TeamService(tdao,udao,pdao);
    }

    @Test
    public void getProjectTeamTest(){
        when(teamService.getTeamByProject(1)).thenReturn(team);
        Assertions.assertEquals(team,teamService.getTeamByProject(1));
    }

    @Test
    public void testRemoveFromTeam(){
        when(teamService.removeFromTeam(user)).thenReturn(1);
        Assertions.assertEquals(1,teamService.removeFromTeam(user));
    }
}
