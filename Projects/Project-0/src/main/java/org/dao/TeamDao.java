package org.dao;

import org.models.Team;
import org.models.User;

import java.util.List;

public interface TeamDao {
    public boolean createTeam(Team team);

    public int updateTeam(User user);

    public boolean addTeamMember(int team_id, User user);

    public List<User> getTeamMembers(int team_id);

    public Team getTeamByProject(int id);

}
