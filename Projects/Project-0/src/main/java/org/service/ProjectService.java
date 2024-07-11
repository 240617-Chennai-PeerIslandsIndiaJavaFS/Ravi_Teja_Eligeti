package org.service;

import org.controller.Console;
import org.dao.ProjectDao;
import org.dao.ProjectDaoImplementation;
import org.models.Project;
import org.models.Team;
import org.models.User;

import java.util.List;

public class ProjectService {
    ProjectDaoImplementation projectDao;
    Console console;

    public ProjectService(){
        projectDao=new ProjectDaoImplementation();
        console=new Console();
    }

    public ProjectService(ProjectDaoImplementation projectDao){
        this.projectDao=projectDao;
    }

    public Project addProject(Project project){
        Project project1=projectDao.addProject(project);
        return project1;
    }

    public Project getProjectByName(String name){
        Project project2=projectDao.getProjectByName(name);
        return project2;
    }
    public Project getProjectById(int id){
        return projectDao.getProjectById(id);
    }
    public List<Project> getProjectsByManagerId(int id){

        return projectDao.fetchManagerProjects(id);
    }

    public List<User> getTeamMembers(int id){
        return projectDao.getAllProjectMembers(id);
    }
    public List<Project> getMemberProjects(int id){
        return projectDao.getMemberProjects(id);
    }
    public List<Project> fetchAllProjects() {
        return projectDao.fetchAllProjects();
    }

    public int updateProject(Project project){
        return projectDao.updateProject(project);
    }

    public int deleteUserFrom(int user_id,int project_id){
        return projectDao.deleteUserFromProject(user_id,project_id);
    }
}
