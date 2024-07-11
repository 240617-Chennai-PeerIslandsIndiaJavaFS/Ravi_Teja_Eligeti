package org.dao;

import org.models.Project;
import org.models.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface ProjectDao {
    public Project addProject(Project project);

    public Project getProjectById(int id);

    public List<Project> fetchAllProjects();

    public boolean addMemberIntoProject(int project_id, int user_id);

    public List<User> getAllProjectMembers(int id);

    public List<Project>  getMemberProjects(int user_id);
    public int updateProject(Project project);

    public int deleteUserFromProject(int user_id,int project_id);


}
