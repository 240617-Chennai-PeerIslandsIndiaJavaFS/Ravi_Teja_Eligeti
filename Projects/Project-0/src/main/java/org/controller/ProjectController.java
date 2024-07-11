package org.controller;

import org.models.Project;
import org.models.Task;
import org.models.User;
import org.service.ProjectService;
import org.service.TaskService;

import java.util.List;

public class ProjectController {

    ProjectService service;

    public Project addProject(Project project){
        service=new ProjectService();
        return service.addProject(project);
    }

    public List<Project> getManagersProject(int id){
        service=new ProjectService();
        return service.getProjectsByManagerId(id);
    }

    public Project getProjectById(int id){
        return new ProjectService().getProjectById(id);
    }

    public List<User> projectMembers(int id){
        return new ProjectService().getTeamMembers(id);
    }

    public List<Project> projectsOfMembers(int user_id){
//        System.out.prin;
        return new ProjectService().getMemberProjects(user_id);
    }

    public List<Project> fetchAllProjects(){
        return new ProjectService().fetchAllProjects();
    }

    public int updateProject(Project project){
        return new ProjectService().updateProject(project);
    }

    public int deleteUserFromProject(int user_id,int project_id){
        return new ProjectService().deleteUserFrom(user_id, project_id);
    }

    public Project getProjectByName(String name){
        return new ProjectService().getProjectByName(name);
    }



}
